package prj1.services;

import prj1.domains.DetailPromotion;

import java.util.List;

public interface DetailPromotionService {

    void deleteDetailPromotion(String detailPromotionId);

    List<DetailPromotion> getDetailPromotionByIdGroupProduct(String groupId);

    List<DetailPromotion> getDetailPromotionByStatusPromotion();

    List<DetailPromotion> getDetailPromotionByPromotionId(String promotionID);
}
