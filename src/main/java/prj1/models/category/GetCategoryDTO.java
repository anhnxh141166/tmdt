package prj1.models.category;

import lombok.*;
import prj1.domains.Category;

import java.util.ArrayList;
import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Data
public class GetCategoryDTO extends BaseCategoryDTO {
    List<GetCategoryDTO> categoryDtoList;

    public GetCategoryDTO(Category category) {
        super(category);
        this.categoryDtoList = new ArrayList<>();
    }

}
