package prj1.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import prj1.models.product.GetGroupProductDetailDTO;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Document("GroupProductDetail")
public class GroupProductDetail {
    @Id
    private String id;

    private String productId;

    @DBRef
    @JsonIgnore
    private List<GroupProduct> groupProducts;
//    private List<String> groupProductIDs;

    private String mapID;

    private Integer quantity;

    private Double basePrice;

    private Double discountPrice;

    private String sku;

    private Integer status = 1;

    public GroupProductDetail(GetGroupProductDetailDTO dto) {
        this.quantity = dto.getQuantity();
        this.basePrice = dto.getBasePrice();
        this.discountPrice = dto.getDiscountPrice();
        this.sku = dto.getSku();
        this.mapID = dto.getId();
    }

    public GroupProductDetail(GetGroupProductDetailDTO dto, String productId) {
        this.quantity = dto.getQuantity();
        this.basePrice = dto.getBasePrice();
        this.discountPrice = dto.getDiscountPrice();
        this.sku = dto.getSku();
        this.mapID = dto.getId();
        this.productId = productId;
    }

    public GroupProductDetail update(GetGroupProductDetailDTO dto) {
        this.id = dto.getId();
        this.quantity = dto.getQuantity();
        this.basePrice = dto.getBasePrice();
        this.discountPrice = dto.getDiscountPrice();
        this.sku = dto.getSku();
        return this;
    }

    public void addGroupProduct(GroupProduct groupProduct) {
        if(this.groupProducts == null) {
            this.groupProducts = new ArrayList<>();
        }
        this.groupProducts.add(groupProduct);
    }
}
