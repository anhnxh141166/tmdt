package prj1.models.category;

import lombok.Data;
import lombok.EqualsAndHashCode;
import prj1.models.productproperty.ProductPropertyDTO;
import prj1.models.tag.TagDto;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RequestCategory extends BaseCategoryDTO{
    List<TagDto> tagDtoList;
    List<ProductPropertyDTO> productPropertyDTOList;
}
