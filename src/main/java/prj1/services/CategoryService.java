package prj1.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import prj1.domains.Category;
import prj1.models.category.*;
import prj1.models.productproperty.ProductPropertyDTO;
import prj1.models.tag.TagDto;

import java.util.List;

public interface CategoryService {

    void createCategory(BaseCategoryDTO category);

    Page<BaseCategoryDTO> search(String name, Pageable pageable);

    Category getCategoryById(String id);

    List<String> getTreeCategoryById(String id);

    TagPropertyDTO getTagPropertyByCategoryId(String id);

    void updateCategory(String id, BaseCategoryDTO category);

    void deleteCategory(String id);

    void createTagProperty(String id, TagPropertyDTO dto);

    void updateTagProperty(String id, TagPropertyDTO dto);

    void createCategoryTagProperty(RequestCategory requestCategory);
}
