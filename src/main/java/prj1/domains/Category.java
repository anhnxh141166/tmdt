package prj1.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import prj1.models.category.BaseCategoryDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Document("Category")
public class Category {
    @Id
    private String categoryId;
    private String categoryParentId;
    private String categoryName;

    private int status = 1;

    @DBRef
    List<ProductProperty> productProperties;
    @DBRef
    List<Tag> tags;

    public Category(BaseCategoryDTO baseCategoryDTO) {
        this.categoryId = baseCategoryDTO.getCategoryId();
        this.categoryParentId = baseCategoryDTO.getCategoryParentId();
        this.categoryName = baseCategoryDTO.getCategoryName();
    }

    public void updateCategory(BaseCategoryDTO baseCategoryDTO) {
        this.categoryId = baseCategoryDTO.getCategoryId();
        this.categoryParentId = baseCategoryDTO.getCategoryParentId();
        this.categoryName = baseCategoryDTO.getCategoryName();
    }
}
