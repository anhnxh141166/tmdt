package prj1.models.category;

import lombok.*;
import prj1.domains.Category;
import prj1.models.product.TagDTO;

@Data
@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
public class BaseCategoryDTO {
    private String categoryId;
    private String categoryParentId;
    private String categoryName;
//    List<BaseCategoryDto> baseCategoryDtoList;

    public BaseCategoryDTO(Category category) {
        this.categoryId = category.getCategoryId();
        this.categoryParentId = category.getCategoryParentId();
        this.categoryName = category.getCategoryName();
//        this.baseCategoryDtoList = new ArrayList<>();
    }
}
