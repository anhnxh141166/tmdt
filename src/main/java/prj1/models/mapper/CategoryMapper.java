package prj1.models.mapper;

import org.modelmapper.ModelMapper;
import prj1.domains.Category;
import prj1.models.category.GetCategoryDTO;

public class CategoryMapper {
    private final ModelMapper modelMapper;

    public CategoryMapper() {
        this.modelMapper = new ModelMapper();
    }

    public GetCategoryDTO convertToDto(Category category){
        return modelMapper.map(category, GetCategoryDTO.class);
    }
}
