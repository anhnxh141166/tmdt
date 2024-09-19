package prj1.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import prj1.models.promotion.PromotionRequets;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

//@Entity

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Document(collection = "Promotion")
public class Promotion {
    @Id
    private String id;

    private String name;

    private String description;

    private LocalDateTime startDay;

    private LocalDateTime endDay;

    private Integer status;

    private Integer type;// %: 1 || - tien: 2

    private Integer tools; // hoa don  1 || saon pham : 2


     // ( QUANTITY* PRICE * PROMOtYPE1_4   - PROMOTYPE2_4  +   QUANTITY* PRICE * PROMOtYPE1_4   -  PROMOtYPE2_4 ) * POMMOtYPE1_3  - PROMOtYPE2_3

    // type  = 1 giảm giá theo hóa đơn ( giảm giá % hóa đơn, giảm giá số tiền,
    // giảm hết sản phẩm, giảm theo 1 số sản phẩm)
    // =2 giảm giá sản phẩm (giảm theo %, giảm giá tiền)

    private LocalDate createdDate;

    private LocalDate updatedDate;

    private String createdBy;

    private String updatedBy;

    @DBRef
    @JsonIgnore
    private List<DetailPromotion> detailPromotions;

    @DBRef
    private Shop shop;

    public Promotion(PromotionRequets requets) {
        this.setName(requets.getName());
        this.setDescription(requets.getDescription());
        this.setStartDay(requets.getStartDay());
        this.setEndDay(requets.getEndDay());
        this.setStatus(requets.getStatus());
        this.setType(requets.getType());
        this.setTools(requets.getTools());
        this.setShop(Shop.builder().id(requets.getIdShop()).build());
    }

    public void getPromotionUpdate(PromotionRequets promotionRequets) {
        this.setStartDay(promotionRequets.getStartDay());
        this.setEndDay(promotionRequets.getEndDay());
        this.setDescription(promotionRequets.getDescription());
        this.setStatus(promotionRequets.getStatus());
        this.setType(promotionRequets.getType());
        this.setTools(promotionRequets.getTools());
        this.setShop(Shop.builder().id(promotionRequets.getIdShop()).build());
    }
}
