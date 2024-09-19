package prj1.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import prj1.domains.Promotion;
import prj1.models.promotion.PromotionDTO;
import prj1.models.promotion.PromotionRequets;

public interface PromotionService {
    Page<Promotion> getAll(Pageable pageable);

    void createPromotion(PromotionRequets promotionRequets);

    void updatePromotion(String name, PromotionRequets promotionRequets);

    void removePromotion(String name);

    Promotion getDetailPromotionById(String id);

    Page<Promotion> getAllPromotion(Integer status, String search, Pageable pageable);

    Page<PromotionDTO> getAllPromotionDTO(String name, Integer status, Pageable pageable);
}
