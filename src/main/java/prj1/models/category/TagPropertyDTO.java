package prj1.models.category;

import lombok.Data;
import lombok.EqualsAndHashCode;
import prj1.models.productproperty.ProductPropertyDTO;
import prj1.models.tag.TagDto;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class TagPropertyDTO extends BaseCategoryDTO {
    List<ProductPropertyDTO> productPropertyDTOList;
    List<TagDto> tagDtoList;
}
