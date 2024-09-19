package prj1.models.category;

import lombok.Data;
import lombok.EqualsAndHashCode;
import prj1.models.productproperty.ProductPropertyDTO;

import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryPropertyDTO extends BaseCategoryDTO{
    List<ProductPropertyDTO> productPropertyDTOList;
}
