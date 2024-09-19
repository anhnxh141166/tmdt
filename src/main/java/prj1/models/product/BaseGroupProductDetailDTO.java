package prj1.models.product;

import lombok.Data;

@Data
public class BaseGroupProductDetailDTO {
   private String id;

   private Integer quantity;

   private Double basePrice;

   private Double discountPrice;

   private String sku;

   private Integer status;
}
