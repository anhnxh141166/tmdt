package prj1.models.productpropertyvalue;

import lombok.Data;
import lombok.NoArgsConstructor;
import prj1.domains.ProductPropertyValue;

@Data
@NoArgsConstructor
public class ProductPropertyValueDTO {
    private String id;
    private String productPropertyValueName;

    public ProductPropertyValueDTO(ProductPropertyValue productPropertyValue) {
        this.id = productPropertyValue.getId();
        this.productPropertyValueName = productPropertyValue.getProductPropertyValueName();
    }

    public void update(ProductPropertyValue productPropertyValue) {
        this.id = productPropertyValue.getId();
        this.productPropertyValueName = productPropertyValue.getProductPropertyValueName();
    }
}
