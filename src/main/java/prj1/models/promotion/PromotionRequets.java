package prj1.models.promotion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import prj1.domains.DetailPromotion;
import prj1.domains.GroupProduct;
import prj1.domains.Promotion;
import prj1.domains.Shop;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PromotionRequets {
    private String name;

    private Integer percent;

    private String description;

    private Double minimumOder;

    private Double reducedMoney;

    private Integer status;

    private LocalDateTime startDayProduct;

    private LocalDateTime endDayProduct;

    private Double discount; //

    private Double maximumDiscount;

    private Integer type;

    private Integer tools; // công cụ quảng bá (hóa đơn hay sản phẩm)

    private Integer quantity;

    private Integer limitPerGuest;

    private LocalDateTime startDay;

    private LocalDateTime endDay;

    private List<DetailPromotionDTO> detailPromotions;

    private String idShop;

}
