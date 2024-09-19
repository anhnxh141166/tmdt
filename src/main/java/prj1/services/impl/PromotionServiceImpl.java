package prj1.services.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import prj1.domains.DetailPromotion;
import prj1.domains.GroupProduct;
import prj1.domains.Promotion;
import prj1.exceptions.CustomException;
import prj1.exceptions.ExceptionUtils;
import prj1.models.promotion.DetailPromotionDTO;
import prj1.models.promotion.PromotionDTO;
import prj1.models.promotion.PromotionRequets;
import prj1.repositories.DetailPromotionRepository;
import prj1.repositories.GroupProductRepository;
import prj1.repositories.PromotionRepository;
import prj1.services.DetailPromotionService;
import prj1.services.PromotionService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;

    private final DetailPromotionRepository detailPromotionRepository;

    private final DetailPromotionService detailPromotionService;

    private final GroupProductRepository groupProductRepository;

    private final MongoTemplate mongoTemplate;

    @Override
    public Page<Promotion> getAll(Pageable pageable) {
        return promotionRepository.findAll(pageable);
    }


    public void createPromotionNew(PromotionRequets promotionRequets) {
        // tìm kiếm promotion đã tồn tại
        Optional<Promotion> promotionOptional = promotionRepository.findPromotionByName(promotionRequets.getName());
        if (promotionOptional.isPresent()) {
            throw new CustomException(ExceptionUtils.FIND_PROMOOTION);
        }
        // khi bắn ra throw vẫn thêm mới
        Promotion promotion = new Promotion(promotionRequets);

        List<DetailPromotionDTO> detailPromotionDTOList = promotionRequets.getDetailPromotions();
        List<DetailPromotion> listDetailPromotion = new ArrayList<>();
        List<GroupProduct> listGroupProduct = new ArrayList<>();
        if (!CollectionUtils.isEmpty(detailPromotionDTOList)) {

            List<String> idGrpLists = detailPromotionDTOList.stream().map(DetailPromotionDTO::getIdGroupProduct).toList();
            List<GroupProduct> allByIdIn = groupProductRepository.findAllByIdIn(idGrpLists);
            if (idGrpLists.size() != allByIdIn.size()) {
                throw new CustomException(ExceptionUtils.SIZE_GROUPPRODUCT);
            }
            // lấy ra những groupProduct từ detailPromotionDto
//            Map<String, GroupProduct> groupProductMap = getGrProByDePromoDTO(detailPromotionDTOList);
            // tao moi listDetailPromotion
            DetailPromotion detailPromotionA;
            GroupProduct groupProduct;
            Optional<GroupProduct> gr;
            for (DetailPromotionDTO detailPromotion : detailPromotionDTOList) {
                detailPromotionA = new DetailPromotion(promotionRequets, detailPromotion);
                gr = allByIdIn.stream()
                        .filter(i -> StringUtils.equals(detailPromotion.getIdGroupProduct(), i.getId())).findFirst();

                if (gr.isEmpty()) {
                    throw new CustomException(ExceptionUtils.NOT_FIND_GROUPPRODUCT);
                }
                groupProduct = gr.get();
                detailPromotionA.setGroupProduct(groupProduct);
                detailPromotionA.setPromotion(promotion);
                listDetailPromotion.add(detailPromotionA);

                groupProduct.addDetailPromotion(detailPromotionA);

                listGroupProduct.add(groupProduct);
            }
        } else {
            DetailPromotion detailPromotionHD = new DetailPromotion(promotionRequets);
            detailPromotionHD.setPromotion(promotion);
            listDetailPromotion.add(detailPromotionHD);
        }
        promotionRepository.save(promotion);
        detailPromotionRepository.saveAll(listDetailPromotion);
        if (!CollectionUtils.isEmpty(listGroupProduct)) {
            groupProductRepository.saveAll(listGroupProduct);
        }
        promotion.setDetailPromotions(listDetailPromotion);
        promotionRepository.save(promotion);

    }

    public void updatePromotionU(String promotionID, PromotionRequets promotionRequets) {
        Promotion promotionU = getDetailPromotionById(promotionID);
        promotionU.getPromotionUpdate(promotionRequets);

        List<DetailPromotionDTO> detailPromotionDTOList = promotionRequets.getDetailPromotions();
        if (CollectionUtils.isEmpty(detailPromotionDTOList)) {
            throw new IllegalArgumentException();
        }
        List<DetailPromotion> detailPromotionList = new ArrayList<>();

        List<String> idDePromo = detailPromotionDTOList.stream()
                .map(DetailPromotionDTO::getIdDetailPromotion)
                .filter(Objects::nonNull)
                .toList();
        List<DetailPromotion> allByIdInDe = detailPromotionRepository.findAllByIdIn(idDePromo);
        List<String> idGrpLists = detailPromotionDTOList.stream()
                .map(DetailPromotionDTO::getIdGroupProduct)
                .filter(Objects::nonNull)
                .toList();
        List<GroupProduct> allByIdInGr = groupProductRepository.findAllByIdIn(idGrpLists);
        if (idGrpLists.size() != allByIdInGr.size() || idDePromo.size() != allByIdInDe.size()) {
            throw new CustomException(ExceptionUtils.SIZE_GROUPPRODUCT_AND_DETAILPROMOTION);
        }
        Optional<GroupProduct> gr;
        Optional<DetailPromotion> optionalDetailPromotion;
        DetailPromotion detailPromotion;
        GroupProduct groupProduct;
        for (DetailPromotionDTO detailPromotionU : detailPromotionDTOList) {
            gr = allByIdInGr.stream().filter(i -> StringUtils.equals(detailPromotionU.getIdGroupProduct(), i.getId())).findFirst();
            if (detailPromotionU.getIdDetailPromotion() != null) {
                optionalDetailPromotion = allByIdInDe.stream().
                        filter(i -> StringUtils.equals(detailPromotionU.getIdDetailPromotion(), i.getId())).findFirst();
                if (optionalDetailPromotion.isEmpty()) {
                    throw new CustomException(ExceptionUtils.NOT_FIND_DETAILPROMOTION);
                }
                detailPromotion = optionalDetailPromotion.get();
                if (detailPromotionU.getIdGroupProduct() != null && gr.isPresent()) {
                    groupProduct = gr.get();
                    detailPromotion.setDetailPromotionUpdate(detailPromotionU, promotionRequets);
                    detailPromotion.setGroupProduct(groupProduct);
                } else {
                    detailPromotion.setDetailPromotionHDUpdate(detailPromotionU, promotionRequets);
                }
            } else {
                if (gr.isEmpty()) {
                    throw new CustomException(ExceptionUtils.NOT_FIND_GROUPPRODUCT);
                }
                detailPromotion = new DetailPromotion(promotionRequets, detailPromotionU);
                detailPromotion.setPromotion(promotionU);
                groupProduct = gr.get();
                detailPromotion.setGroupProduct(groupProduct);

            }
            detailPromotionList.add(detailPromotion);
            detailPromotionRepository.saveAll(detailPromotionList);
            promotionU.setDetailPromotions(detailPromotionList);
            promotionRepository.save(promotionU);
        }
    }

    @Override
    public void createPromotion(PromotionRequets promotionRequets) {
        createPromotionNew(promotionRequets);
    }

    @Override
    public void updatePromotion(String idPromotion, PromotionRequets promotionRequets) {
        updatePromotionU(idPromotion, promotionRequets);
    }

    @Override
    public void removePromotion(String name) {
        Optional<Promotion> promotionOptional = promotionRepository.findPromotionByName(name);
        if (promotionOptional.isEmpty()) {
            throw new CustomException(ExceptionUtils.NOT_FIND_PROMOOTION);
        }
        Promotion promotion = promotionOptional.get();
        promotion.setStatus(2);
        // 2 : dung hoat dong
        List<DetailPromotion> detailPromotions = detailPromotionService.getDetailPromotionByPromotionId(promotion.getId());
        List<DetailPromotion> detailPromotionListAfterU = new ArrayList<>();
        for (DetailPromotion detailPromotion : detailPromotions) {
            detailPromotion.setStatus(2);
            detailPromotionListAfterU.add(detailPromotion);
        }
        detailPromotionRepository.saveAll(detailPromotionListAfterU);
        promotionRepository.save(promotion);

    }

    @Override
    public Promotion getDetailPromotionById(String id) {
        return promotionRepository.findPromotionById(id).orElseThrow(() ->
                new CustomException(ExceptionUtils.NOT_FIND_PROMOOTION));
    }

    @Override
    public Page<Promotion> getAllPromotion(Integer status, String search, Pageable pageable) {
        return promotionRepository.getAllPromotion(status, search, pageable);
    }

    @Override
    public Page<PromotionDTO> getAllPromotionDTO(String name, Integer status, Pageable pageable) {
        Query query = new Query();

        if (name != null) {
            query.addCriteria(Criteria.where("name").is(name));
        }
        if (name != null) {
            query.addCriteria(Criteria.where("startDay").is(name));
        }
        if (status != null) {
            query.addCriteria(Criteria.where("status").is(status));
        }

        long totalCount = mongoTemplate.count(query, Promotion.class);
        query.with(pageable);

        List<Promotion> promotions = mongoTemplate.find(query, Promotion.class);
        List<PromotionDTO> promotionDTOs = promotions.stream().map(PromotionDTO::new).collect(Collectors.toList());

        return new PageImpl<>(promotionDTOs, pageable, totalCount);
    }

}
