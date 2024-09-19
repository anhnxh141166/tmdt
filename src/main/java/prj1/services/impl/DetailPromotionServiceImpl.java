package prj1.services.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import prj1.domains.DetailPromotion;
import prj1.domains.Promotion;
import prj1.models.promotion.PromotionRequets;
import prj1.repositories.DetailPromotionRepository;
import prj1.repositories.PromotionRepository;
import prj1.services.DetailPromotionService;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DetailPromotionServiceImpl implements DetailPromotionService {
    @Autowired
    DetailPromotionRepository detailPromotionRepository;

    @Autowired
    PromotionRepository promotionRepository;


    private final MongoTemplate mongoTemplate;

    public DetailPromotionServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void deleteDetailPromotion(String detailPromotionId) {
        detailPromotionRepository.delete(detailPromotionRepository.findById(detailPromotionId).orElse(new DetailPromotion()));
    }

    @Override
    public List<DetailPromotion> getDetailPromotionByIdGroupProduct(String groupId) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        List<Promotion> promotionList = promotionRepository.findAll();
        LocalDateTime now = LocalDateTime.now();
        List<Criteria> orCriteriaList = new ArrayList<>();

        if (groupId != null) {
            criteria.and("groupProduct.id").is(groupId);
        }

        for (Promotion promotion : promotionList) {
           for (DetailPromotion detailPromotion : promotion.getDetailPromotions())
            if (promotion.getEndDay().isAfter(now) && promotion.getStartDay().isBefore(now)) {
                if(detailPromotion.getEndDayProduct().isAfter(now) && detailPromotion.getStartDayProduct().isBefore(now)){
                    orCriteriaList.add(Criteria.where("promotion.id").is(promotion.getId()));
                }
            }
        }

        if (!orCriteriaList.isEmpty()) {
            criteria.orOperator(orCriteriaList.toArray(new Criteria[0]));
            query.addCriteria(criteria);
        } else {
            return Collections.emptyList();
        }

        return mongoTemplate.find(query, DetailPromotion.class);
    }

    @Override
    public List<DetailPromotion> getDetailPromotionByStatusPromotion() {
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("Promotion")
                .localField("promotion.$id")
                .foreignField("_id")
                .as("promotion_detail");

        UnwindOperation unwindOperation = Aggregation.unwind("$promotion_detail");

        Criteria statusCriteria = Criteria.where("promotion_detail.status").is(1);
        MatchOperation matchOperation = Aggregation.match(statusCriteria);

        Aggregation aggregation = Aggregation.newAggregation(lookupOperation, unwindOperation, matchOperation);

        return mongoTemplate.aggregate(aggregation, "DetailPromotion", DetailPromotion.class).getMappedResults();
    }

    @Override
    public List<DetailPromotion> getDetailPromotionByPromotionId(String idPromotion) {
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("Promotion")
                .localField("promotion.$id")
                .foreignField("_id")
                .as("promotion_detail");

        UnwindOperation unwindOperation = Aggregation.unwind("promotion_detail");

        Criteria statusCriteria = Criteria.where("promotion_detail._id").is(new ObjectId(idPromotion));
        MatchOperation matchOperation = Aggregation.match(statusCriteria);

        Aggregation aggregation = Aggregation.newAggregation(lookupOperation, unwindOperation, matchOperation);

        return mongoTemplate.aggregate(aggregation, "DetailPromotion", DetailPromotion.class).getMappedResults();
    }

}
