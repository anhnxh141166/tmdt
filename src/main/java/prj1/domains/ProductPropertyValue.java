package prj1.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import prj1.models.productpropertyvalue.ProductPropertyValueDTO;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Document("ProductPropertyValue")
public class ProductPropertyValue {
//    @Transient
//    public static final String SEQUENCE_NAME = "users_sequence";
    @Id
    private String id;
    private String productPropertyValueName;

    private Integer status = 1;
    @DBRef
    private GroupProductDetail groupProductDetail;

    @DBRef
    private ProductProperty productProperty;

    public ProductPropertyValue(ProductPropertyValueDTO productPropertyValueDto){
        this.id = productPropertyValueDto.getId();
        this.productPropertyValueName = productPropertyValueDto.getProductPropertyValueName();

    }

    public void update(ProductPropertyValueDTO productPropertyValueDTO){
        this.id = productPropertyValueDTO.getId();
        this.productPropertyValueName = productPropertyValueDTO.getProductPropertyValueName();
    }

}
