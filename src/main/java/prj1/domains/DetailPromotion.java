package prj1.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import prj1.models.promotion.DetailPromotionDTO;
import prj1.models.promotion.PromotionRequets;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Document("DetailPromotion")
public class DetailPromotion {
    @Id
    private String id;

    @DBRef
    private GroupProduct groupProduct;
//    private String groupproductID;

    @DBRef
    private Promotion promotion;

    private Integer quantity;

    private Integer quantityUsed;

    private Integer limitPerGuest; // giới hạn cho mỗi khách hàng/

    private Double minimumOder; // số tiền chi tối thiểu/

    private Double reducedMoney; // số tiền chiết khấu/

    private Integer percent; // % giảm sản phẩm/

    private Integer status;

    private LocalDateTime startDayProduct;

    private LocalDateTime endDayProduct;

    private Double discount; // giá ưu đãi/

    private Double maximumDiscount; //  giảm giá tối đa/

    private LocalDate createdDate;

    private LocalDate updatedDate;

    private String createdBy;

    private String updatedBy;

    public DetailPromotion(PromotionRequets requets) {
        this.setQuantity(requets.getQuantity());
        this.setLimitPerGuest(requets.getLimitPerGuest());
        this.setPercent(requets.getPercent());
        this.setDiscount(requets.getDiscount());
        this.setMinimumOder(requets.getMinimumOder());
        this.setReducedMoney(requets.getReducedMoney());
        this.setPercent(requets.getPercent());
        this.setMaximumDiscount(requets.getMaximumDiscount());
        this.setStartDayProduct(requets.getStartDayProduct());
        this.setEndDayProduct(requets.getEndDayProduct());
    }
    public DetailPromotion(PromotionRequets promotionRequets,
                           DetailPromotionDTO detailPromotionDTO) {
        this.setDiscount(detailPromotionDTO.getDiscount());
        this.setPercent(detailPromotionDTO.getPercent());
        this.setLimitPerGuest(detailPromotionDTO.getLimitPerGuest());
        this.setQuantity(detailPromotionDTO.getQuantity());
        this.setStartDayProduct(detailPromotionDTO.getStartDayProduct());
        this.setEndDayProduct(detailPromotionDTO.getEndDayProduct());

        if (promotionRequets.getType() == 2 && promotionRequets.getTools() == 1) {
            this.setMinimumOder(detailPromotionDTO.getMinimumOder());
            this.setReducedMoney(detailPromotionDTO.getReducedMoney());
        } else if (promotionRequets.getType() == 1 && (promotionRequets.getTools() == 1 || promotionRequets.getTools() == 2)) {
            this.setMinimumOder(detailPromotionDTO.getMinimumOder());
            this.setMaximumDiscount(detailPromotionDTO.getMaximumDiscount());
        }


    }


    public void setDetailPromotionUpdate( DetailPromotionDTO detailPromotionDTO, PromotionRequets promotionRequets) {

        this.setDiscount(detailPromotionDTO.getDiscount());
        this.setPercent(detailPromotionDTO.getPercent());
        this.setLimitPerGuest(detailPromotionDTO.getLimitPerGuest());
        this.setQuantity(detailPromotionDTO.getQuantity());
        this.setStartDayProduct(detailPromotionDTO.getStartDayProduct());
        this.setEndDayProduct(detailPromotionDTO.getEndDayProduct());
        if (promotionRequets.getType() == 2 && promotionRequets.getTools() == 1) {
            this.setMinimumOder(detailPromotionDTO.getMinimumOder());
            this.setReducedMoney(detailPromotionDTO.getReducedMoney());
        } else if (promotionRequets.getType() == 1 && (promotionRequets.getTools() == 1 || promotionRequets.getTools() == 2)) {
            this.setMinimumOder(detailPromotionDTO.getMinimumOder());
            this.setMaximumDiscount(detailPromotionDTO.getMaximumDiscount());
        }
    }

    public void setDetailPromotionHDUpdate( DetailPromotionDTO detailPromotionDTO, PromotionRequets promotionRequets) {
        if (promotionRequets.getType() == 1) {
            this.setPercent(detailPromotionDTO.getPercent());
            this.setMaximumDiscount(detailPromotionDTO.getMaximumDiscount());
        } else {
            this.setReducedMoney(detailPromotionDTO.getReducedMoney());
        }
        this.setLimitPerGuest(detailPromotionDTO.getLimitPerGuest());
        this.setQuantity(detailPromotionDTO.getQuantity());
        this.setMinimumOder(detailPromotionDTO.getMinimumOder());
    }
}
