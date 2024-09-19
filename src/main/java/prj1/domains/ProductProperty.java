package prj1.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import prj1.models.productproperty.ProductPropertyDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Document("ProductProperty")
public class ProductProperty {
    @Id
    private String productPropertyId;
    private String productPropertyName;
    @DBRef
    private List<Category> categoryList;
    private int status = 1;
    @DBRef
    private List<ProductPropertyValue> productPropertyValueList;

    public ProductProperty(ProductPropertyDTO productPropertyDto) {
        this.productPropertyId = productPropertyDto.getProductPropertyId();
        this.productPropertyName = productPropertyDto.getProductPropertyName();
    }

    public void update(ProductPropertyDTO property) {
        this.productPropertyId = property.getProductPropertyId();
        this.productPropertyName = property.getProductPropertyName();
    }
}
