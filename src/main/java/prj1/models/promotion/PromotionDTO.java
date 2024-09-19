package prj1.models.promotion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import prj1.domains.DetailPromotion;
import prj1.domains.Promotion;
import prj1.domains.Shop;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDTO {

    private String id;

    private String idGroupProduct;

    private String name;

    private Integer type;

    private LocalDateTime startDay;

    private LocalDateTime endDay;

    private Integer discountPercent;

    private Integer discount;

    private Integer limitPerGuest;
//    private Double

    private Integer quantity;

    private List<DetailPromotionDTO> detailPromotions;

    private Shop shop;

    public PromotionDTO(Promotion promotion) {
        this.setId(promotion.getId());
        this.setName(promotion.getName());
        this.setType(promotion.getType());
        this.setStartDay(promotion.getStartDay());
        this.setEndDay(promotion.getEndDay());
        this.setShop(promotion.getShop());
        List<DetailPromotionDTO> detailPromotionDTOs = new ArrayList<>();

        for (DetailPromotion detailPromotion : promotion.getDetailPromotions()) {
            DetailPromotionDTO detailPromotionDTO = new DetailPromotionDTO();

            if (detailPromotion != null) {
                if (detailPromotion.getGroupProduct() != null) {
                    detailPromotionDTO.setIdGroupProduct(detailPromotion.getGroupProduct().getId());
                    detailPromotionDTO.setQuantity(detailPromotion.getQuantity());
                    detailPromotionDTO.setPercent(detailPromotion.getPercent());
                    detailPromotionDTO.setDiscount(detailPromotion.getDiscount());
                    detailPromotionDTO.setLimitPerGuest(detailPromotion.getLimitPerGuest());
                    detailPromotionDTO.setMinimumOder(detailPromotion.getMinimumOder());
                    detailPromotionDTO.setReducedMoney(detailPromotion.getReducedMoney());
                    detailPromotionDTO.setMaximumDiscount(detailPromotion.getMaximumDiscount());
                } else {
                    detailPromotionDTO.setMinimumOder(detailPromotion.getMinimumOder());
                    detailPromotionDTO.setLimitPerGuest(detailPromotion.getLimitPerGuest());
                    detailPromotionDTO.setQuantity(detailPromotion.getQuantity());
                    detailPromotionDTO.setReducedMoney(detailPromotion.getReducedMoney());
                    detailPromotionDTO.setPercent(detailPromotion.getPercent());
                }
            }

            detailPromotionDTOs.add(detailPromotionDTO);
        }

        this.setDetailPromotions(detailPromotionDTOs);
    }

    public void setDetailPromotions(List<DetailPromotionDTO> detailPromotions) {
        this.detailPromotions = detailPromotions;
    }


}
