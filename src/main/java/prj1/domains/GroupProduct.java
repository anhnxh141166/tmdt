package prj1.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.util.CollectionUtils;
import prj1.models.product.PostGroupProductDTO;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Document("GroupProduct")
public class GroupProduct {
   @Id
   private String id;

   private String name;

   private String code;

   private Integer status = 0;

   @DBRef
   @JsonIgnore
   private List<GroupProductDetail> groupProductDetails;

   @DBRef
   @JsonIgnore
   private List<DetailPromotion> detailPromotions;

   public GroupProduct(PostGroupProductDTO dto) {
      this.name = dto.getName();
      this.code = dto.getCode();
   }

   public void update(PostGroupProductDTO dto) {
      this.name = dto.getName();
      this.code = dto.getCode();
   }

   public void addDetailPromotion( DetailPromotion detailPromotion){
      if (CollectionUtils.isEmpty(detailPromotions)){
         detailPromotions = new ArrayList<>();
      }
      detailPromotions.add(detailPromotion);
   }
}
