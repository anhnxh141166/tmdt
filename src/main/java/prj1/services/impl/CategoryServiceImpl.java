package prj1.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import prj1.domains.Category;
import prj1.domains.ProductProperty;
import prj1.domains.Tag;
import prj1.exceptions.CustomException;
import prj1.exceptions.ExceptionUtils;
import prj1.models.category.BaseCategoryDTO;
import prj1.models.category.GetCategoryDTO;
import prj1.models.category.RequestCategory;
import prj1.models.category.TagPropertyDTO;
import prj1.models.productproperty.ProductPropertyDTO;
import prj1.models.tag.TagDto;
import prj1.repositories.CategoryRepository;
import prj1.repositories.ProductPropertyRepository;
import prj1.repositories.TagRepository;
import prj1.services.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductPropertyRepository productPropertyRepository;

    private final TagRepository tagRepository;
    @Autowired
    MongoTemplate mongoTemplate;


//    @Override
//    public Page<BaseCategoryDTO> search(String name, Pageable pageable) {
//        Criteria criteria = new Criteria();
//        // Add criteria for filtering based on parameters
//        if (name != null && !name.isEmpty()) {
////            query.addCriteria(Criteria.where("categoryName").regex(".*" + name + ".*", "i"));
//            criteria.and("categoryName").regex(name, "i");
//        }
//
//        Query query = new Query(criteria);
//
//
//        // Add pagination information to the query
//        query.with(pageable);
//
//        // Execute the query and retrieve the results
//        List<Category> results = mongoTemplate.find(query, Category.class);
//
//        List<BaseCategoryDTO> baseCategoryDTOList = results
//                .stream()
//                .map(category -> new BaseCategoryDTO(category))
//                .toList();
//
//        // Get the total count for pagination
//        long count = mongoTemplate.count(query, Category.class);
//
//        // Create a Page object with the results and pagination information
//        return new PageImpl<>(baseCategoryDTOList, pageable, count);
//    }

    @Override
    public Page<BaseCategoryDTO> search(String name, Pageable pageable) {
        Query query = new Query();

        if (name != null && !name.isEmpty()) {
            query.addCriteria(Criteria.where("categoryName").regex(name, "i"));
        }

        query.addCriteria(Criteria.where("status").is(1));
        query.with(pageable);
        List<Category> results = mongoTemplate.find(query, Category.class);
        List<BaseCategoryDTO> baseCategoryDTOList = results
                .stream()
                .map(category -> new BaseCategoryDTO(category))
                .toList();
        long count = mongoTemplate.count(query, Category.class);
        return new PageImpl<>(baseCategoryDTOList, pageable, count);
    }


    @Override
    public Category getCategoryById(String id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            throw new CustomException(ExceptionUtils.ITEM_NOT_EXIST);
        }
        return categoryOptional.get();
    }


    public List<GetCategoryDTO> categoryDtoList(String categoryParentId, List<Category> categories) {
        List<GetCategoryDTO> categoryChildDtoList = new ArrayList<>();
        for (Category category : categories) {
            if ((categoryParentId == null && category.getCategoryParentId() == null)
                    || (categoryParentId != null && categoryParentId.equals(category.getCategoryParentId()))) {
                GetCategoryDTO getCategoryDto = new GetCategoryDTO(category);
                List<GetCategoryDTO> categorySubDtoList = categoryDtoList(category.getCategoryId(), categories);
                getCategoryDto.setCategoryDtoList(categorySubDtoList);
                categoryChildDtoList.add(getCategoryDto);
            }
        }
        return categoryChildDtoList;
    }


//    @Override
//    public List<String> getTreeListCategoryId(String id) {
//        List<Category> categories = new ArrayList<>();
//        Optional<Category> categoryOptional = categoryRepository.findById(id);
//        if(categoryOptional.isEmpty()){
//            throw new CustomException(ExceptionUtils.ITEM_NOT_EXIST);
//        }
//        Category childCategory = categoryOptional.get();
//        if (childCategory != null) {
//            categories.add(childCategory);
//        }
//
//        Category parentCategory = null;
//        if (childCategory.getCategoryParentId() != null) {
//            parentCategory = categoryRepository.findById(childCategory.getCategoryParentId()).orElse(null);
//            if (parentCategory != null) {
//                categories.add(parentCategory);
//            }
//        }
//
//        Category grandParentCategory = null;
//        if (parentCategory != null && parentCategory.getCategoryParentId() != null) {
//            grandParentCategory = categoryRepository.findById(parentCategory.getCategoryParentId()).orElse(null);
//            if (grandParentCategory != null) {
//                categories.add(grandParentCategory);
//            }
//        }
//
//        List<String> categoryIdList = new ArrayList<>();
//        for (Category category : categories) {
//            categoryIdList.add(category.getCategoryId());
//        }
//        return categoryIdList;
//    }

    @Override
    public List<String> getTreeCategoryById(String id) { // Get API by id and return ancestors
        List<String> treeCategoryId = new ArrayList<>();
        getCategoryAndParent(id, treeCategoryId);
        return treeCategoryId;
    }

    public void getCategoryAndParent(String id, List<String> categoryIdList) { // Recursive return tree id list
        if (id == null || id.isEmpty()) {
            return;
        }
        Category category = getCategoryById(id);
        categoryIdList.add(category.getCategoryId());
        getCategoryAndParent(category.getCategoryParentId(), categoryIdList);
    }

    //    @Override
//    public TagPropertyDTO getTagPropertyByCategoryId(String id) {
//        TagPropertyDTO tagPropertyDto = new TagPropertyDTO();
//        List<Category> categories = new ArrayList<>();
//
//        Category childCategory = categoryRepository.findById(id).orElse(null);
//        if (childCategory != null) {
//            categories.add(childCategory);
//        }
//
//        Category parentCategory = null;
//        if (childCategory.getCategoryParentId() != null) {
//            parentCategory = categoryRepository.findById(childCategory.getCategoryParentId()).orElse(null);
//            if (parentCategory != null) {
//                categories.add(parentCategory);
//            }
//        }
//
//        Category grandParentCategory = null;
//        if (parentCategory != null && parentCategory.getCategoryParentId() != null) {
//            grandParentCategory = categoryRepository.findById(parentCategory.getCategoryParentId()).orElse(null);
//            if (grandParentCategory != null) {
//                categories.add(grandParentCategory);
//            }
//        }
//
//        List<String> categoryIdList = new ArrayList<>();
//        for (Category category : categories) {
//            categoryIdList.add(category.getCategoryId());
//        }
//        tagPropertyDto = getTagPropertyByCategoryId(categoryIdList);
//        return tagPropertyDto;
//    }
    @Override
    public TagPropertyDTO getTagPropertyByCategoryId(String id) {
        List<String> categoryIdList = getTreeCategoryById(id);
        return getTagPropertyByCategoryIdList(categoryIdList);
    }

    @Override
    public void updateCategory(String id, BaseCategoryDTO categoryRequest) {
        Category category = getCategoryById(id);
        category.updateCategory(categoryRequest);
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(String id) {
        Category category = getCategoryById(id);
        category.setStatus(0);
        categoryRepository.save(category);
    }

    @Override
    public void createTagProperty(String id, TagPropertyDTO dto) {
        Category category = getCategoryById(id);
        List<ProductPropertyDTO> productPropertyDTOList = dto.getProductPropertyDTOList();
        List<TagDto> tagDtoList = dto.getTagDtoList();

        List<ProductProperty> productPropertyList = productPropertyDTOList
                .stream()
                .map(productPropertyDTO -> new ProductProperty(productPropertyDTO))
                .toList();
        category.setProductProperties(productPropertyList);

        List<Tag> tagList = tagDtoList
                .stream()
                .map(tagDto -> new Tag(tagDto))
                .toList();
        category.setTags(tagList);

        categoryRepository.save(category);
    }

    //    @Override
//    public void updateTagProperty(String id, TagPropertyDTO dto) {
//        Category category = getCategoryById(id);
//        List<ProductPropertyDTO> productPropertyDTOList = dto.getProductPropertyDTOList();
//        List<TagDto> tagDtoList = dto.getTagDtoList();
//        List<ProductProperty> existProductPropertyList = category.getProductProperties();
//        List<Tag> existTagList = category.getTags();
//
//        // Product property
//        List<ProductProperty> updatedProductProperty = new ArrayList<>();
//        List<ProductProperty> newProductProperties = new ArrayList<>();
//        for (ProductPropertyDTO productPropertyDTO : productPropertyDTOList) {
//            ProductProperty productProperty = new ProductProperty(productPropertyDTO);
//            if (productProperty.getProductPropertyId() == null) {
//                newProductProperties.add(productProperty);
//            } else {
//                updatedProductProperty.add(productProperty);
//            }
//        }
//        productPropertyRepository.saveAll(newProductProperties);
//        updatedProductProperty.addAll(newProductProperties);
//
//        // Tag
//        List<Tag> updatedTag = new ArrayList<>();
//        List<Tag> newTag = new ArrayList<>();
//        for (TagDto tagDto : tagDtoList) {
//            Tag tag = new Tag(tagDto);
//            if (tagDto.getId() == null) {
//                newTag.add(tag);
//            } else {
//                updatedTag.add(tag);
//            }
//        }
//        tagRepository.saveAll(newTag);
//        updatedTag.addAll(newTag);
//
//        category.setProductProperties(updatedProductProperty);
//        category.setTags(updatedTag);
//        categoryRepository.save(category);
//    }
    @Override
    public void updateTagProperty(String id, TagPropertyDTO dto) {
        Category existCategory = getCategoryById(id);
        List<ProductPropertyDTO> newProductPropertyDTOList = dto.getProductPropertyDTOList();
        List<TagDto> tagDtoList = dto.getTagDtoList();

        // Product property
        List<ProductProperty> updatedProductProperty = updateProperty(newProductPropertyDTOList);

        // Tag
        List<Tag> updatedTag = updateTag(tagDtoList);

        existCategory.setProductProperties(updatedProductProperty);
        existCategory.setTags(updatedTag);
        categoryRepository.save(existCategory);
    }

    @Override
    public void createCategoryTagProperty(RequestCategory requestCategory) {

    }

    public List<ProductProperty> updateProperty(List<ProductPropertyDTO> productPropertyDTOList) {
        List<ProductProperty> updatedProductProperty = new ArrayList<>();
        List<ProductProperty> newProductProperties = new ArrayList<>();
        for (ProductPropertyDTO productPropertyDTO : productPropertyDTOList) {
            ProductProperty productProperty = new ProductProperty(productPropertyDTO);
            if (productProperty.getProductPropertyId() == null) {
                newProductProperties.add(productProperty);
            }
            updatedProductProperty.add(productProperty);
        }
        productPropertyRepository.saveAll(newProductProperties);
        return updatedProductProperty;
    }

    public List<Tag> updateTag(List<TagDto> tagDtoList) {
        List<Tag> updatedTag = new ArrayList<>();
        List<Tag> newTag = new ArrayList<>();
        for (TagDto tagDto : tagDtoList) {
            Tag tag = new Tag(tagDto);
            if (tagDto.getId() == null) {
                newTag.add(tag);
            }
            updatedTag.add(tag);
        }
        tagRepository.saveAll(newTag);
        return updatedTag;
    }

    //    @Override
//    public TagPropertyDTO getTagPropertyByCategoryIdList(List<String> categoryIdList) {
//        TagPropertyDTO tagPropertyDto = new TagPropertyDTO();
//        List<ProductPropertyDTO> productPropertyDTOList = new ArrayList<>();
//        List<TagDto> tagDtoList = new ArrayList<>();
//        List<Category> categoryList = categoryRepository.findAllById(categoryIdList);
//        for (Category category : categoryList) {
//            List<ProductProperty> productProperties = category.getProductProperties();
//            List<Tag> tagList = category.getTags();
//            for (ProductProperty productProperty : productProperties) {
//                ProductPropertyDTO productPropertyDto = new ProductPropertyDTO(productProperty);
////                productPropertyDtoList.add(new ProductPropertyDto(productProperty));
//                productPropertyDTOList.add(productPropertyDto);
//            }
//            tagPropertyDto.setProductPropertyDTOList(productPropertyDTOList);
//            for (Tag tag : tagList) {
//                TagDto tagDto = new TagDto(tag);
//                tagDtoList.add(tagDto);
//            }
//            tagPropertyDto.setTagDtoList(tagDtoList);
//        }
//        return tagPropertyDto;
//    }
    public TagPropertyDTO getTagPropertyByCategoryIdList(List<String> categoryIdList) {
        TagPropertyDTO tagPropertyDto = new TagPropertyDTO();
        List<Category> categoryList = categoryRepository.findAllById(categoryIdList);
        for (Category category : categoryList) {
            List<ProductProperty> productProperties = category.getProductProperties();
            List<Tag> tagList = category.getTags();
            List<ProductPropertyDTO> productPropertyDTOList = productProperties
                    .stream()
                    .map(property -> new ProductPropertyDTO(property))
                    .toList();

            tagPropertyDto.setProductPropertyDTOList(productPropertyDTOList);

            List<TagDto> tagDtoList = tagList.stream()
                    .map(tag -> new TagDto(tag))
                    .toList();
            tagPropertyDto.setTagDtoList(tagDtoList);
        }
        return tagPropertyDto;
    }

//    @Override
//    public void updateProductProperty(String categoryId, CategoryPropertyDto categoryPropertyDto) {
//        Category category = categoryRepository.findById(categoryId).orElse(null);
//
//        if (category != null) {
//            List<ProductPropertyDto> newProductPropertyDtoList = categoryPropertyDto.getProductPropertyDtoList();
//            List<ProductProperty> existingProductProperties = category.getProductProperties();
//
//            for (ProductPropertyDto newPropertyDto : newProductPropertyDtoList) {
//                if (newPropertyDto.getProductPropertyId() == null) {
//                    // Create a new property without an ID and save it
//                    ProductProperty newProperty = new ProductProperty(newPropertyDto);
//                    productPropertyRepository.save(newProperty);
//
//                    // Add the newly created property to the list and update the category
//                    existingProductProperties.add(newProperty);
//                } else {
//                    // Update existing property if it exists
//                    Optional<ProductProperty> existingPropertyOptional = existingProductProperties.stream()
//                            .filter(existingProperty ->
//                                    existingProperty.getProductPropertyId().equals(newPropertyDto.getProductPropertyId()))
//                            .findFirst();
//
//                    existingPropertyOptional.ifPresent(existingProperty ->
//                            existingProperty.setProductPropertyName(newPropertyDto.getProductPropertyName())
//                    );
//                }
//            }
//
//            // Remove properties that are not present in the updated list
//            existingProductProperties.removeIf(existingProperty ->
//                    newProductPropertyDtoList.stream()
//                            .noneMatch(newPropertyDto ->
//                                    Objects.equals(existingProperty.getProductPropertyId(),
//                                            newPropertyDto.getProductPropertyId())
//                            )
//            );
//
//            // Update the category with the modified product properties list
//            category.setProductProperties(existingProductProperties);
//            categoryRepository.save(category);
//        }
//    }


    @Override
    public void createCategory(BaseCategoryDTO categoryRequest) {
        Category category = new Category(categoryRequest);
        categoryRepository.save(category);
    }

}
