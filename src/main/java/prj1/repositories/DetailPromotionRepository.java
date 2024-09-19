package prj1.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import prj1.domains.DetailPromotion;
import prj1.domains.GroupProduct;

import java.util.List;

@Repository
public interface DetailPromotionRepository extends MongoRepository<DetailPromotion, String> {

    List<DetailPromotion> findAllByGroupProductIn(List<GroupProduct> groupProducts);

    List<DetailPromotion> findAllByIdIn(List<String> ids);

}
