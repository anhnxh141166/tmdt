package prj1.models.productproperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import prj1.domains.ProductProperty;
import prj1.models.productpropertyvalue.ProductPropertyValueDTO;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductPropertyDTO {
    private String productPropertyId;
    private String productPropertyName;
    private List<ProductPropertyValueDTO> productPropertyValueDTOList;
    public ProductPropertyDTO(ProductProperty productProperty){
        this.productPropertyId = productProperty.getProductPropertyId();
        this.productPropertyName = productProperty.getProductPropertyName();
    }

//    public void insertPropertyValue()
}
