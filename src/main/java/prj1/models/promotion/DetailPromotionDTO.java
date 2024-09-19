package prj1.models.promotion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailPromotionDTO {

    private String idDetailPromotion;

    private String idGroupProduct;

    private Integer quantityUsed;

    private Double discount;

    private Integer limitPerGuest;

    private Integer percent;

    private LocalDateTime startDayProduct;

    private LocalDateTime endDayProduct;

    private Double minimumOder;

    private Double reducedMoney;

    private Integer quantity;

    private Double maximumDiscount;

}