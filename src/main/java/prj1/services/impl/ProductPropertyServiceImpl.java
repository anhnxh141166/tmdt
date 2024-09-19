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
import prj1.domains.ProductProperty;
import prj1.domains.ProductPropertyValue;
import prj1.exceptions.CustomException;
import prj1.exceptions.ExceptionUtils;
import prj1.models.productproperty.ProductPropertyDTO;
import prj1.models.productpropertyvalue.ProductPropertyValueDTO;
import prj1.repositories.ProductPropertyRepository;
import prj1.repositories.ProductPropertyValueRepository;
import prj1.services.ProductPropertyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductPropertyServiceImpl implements ProductPropertyService {
    private final ProductPropertyRepository productPropertyRepository;
    private final ProductPropertyValueRepository productPropertyValueRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public ProductProperty getProductPropertyById(String id) {
        Optional<ProductProperty> productPropertyOptional = productPropertyRepository.findById(id);
        if (productPropertyOptional.isEmpty()) {
            throw new CustomException(ExceptionUtils.ITEM_NOT_EXIST);
        }
        return productPropertyOptional.get();
    }

    @Override
    public Page<ProductPropertyDTO> search(String name, Pageable pageable) {
        Query query = new Query();
        if (name != null && !name.isEmpty()) {
            query.addCriteria(Criteria.where("productPropertyName").regex(name, "i"));
        }
        query.addCriteria(Criteria.where("status").is(1));
        query.with(pageable);
        List<ProductProperty> productProperties = mongoTemplate.find(query, ProductProperty.class);
        List<ProductPropertyDTO> productPropertyDTOList = productProperties
                .stream()
                .map(productProperty -> new ProductPropertyDTO(productProperty))
                .toList();
        long count = mongoTemplate.count(query, ProductProperty.class);
        return new PageImpl<>(productPropertyDTOList, pageable, count);
    }

    @Override
    public void createProductProperty(ProductPropertyDTO productPropertyDto) {
        ProductProperty productProperty = new ProductProperty(productPropertyDto);
        productPropertyRepository.save(productProperty);
    }

    @Override
    public void deleteProperty(String id) {
        ProductProperty productProperty = getProductPropertyById(id);
        productProperty.setStatus(0);
        productPropertyRepository.save(productProperty);
    }

    @Override
    public void insertPropertyValueToProperty(String id, ProductPropertyDTO productPropertyDTO) {
        ProductProperty productProperty = getProductPropertyById(id);
        productProperty.setProductPropertyName(productPropertyDTO.getProductPropertyName());
        List<ProductPropertyValueDTO> productPropertyValueDTOList = productPropertyDTO.getProductPropertyValueDTOList();
        List<ProductPropertyValue> newPropertyValueList = new ArrayList<>();
        List<ProductPropertyValue> updatePropertyValueList = new ArrayList<>();
        for(ProductPropertyValueDTO productPropertyValueDTO: productPropertyValueDTOList){
            ProductPropertyValue property = new ProductPropertyValue(productPropertyValueDTO);
            if(property == null){
                newPropertyValueList.add(property);
            }else {
                updatePropertyValueList.add(property);
            }
        }
        productPropertyValueRepository.saveAll(newPropertyValueList);
        updatePropertyValueList.addAll(newPropertyValueList);
        productProperty.setProductPropertyValueList(updatePropertyValueList);
        productPropertyRepository.save(productProperty);
    }





    @Override
    public void updateProductProperty(ProductPropertyDTO productPropertyDto) {
        ProductProperty productProperty = getProductPropertyById(productPropertyDto.getProductPropertyId());
        productProperty.update(productPropertyDto);
        productPropertyRepository.save(productProperty);
    }

//    @Override
//    public void updateProductProperty(String id, ProductPropertyDto productPropertyDto) {
//        ProductProperty existingProperty = productPropertyRepository.findById(id).orElse(null);
//        if (existingProperty != null) {
//            existingProperty.setProductPropertyName(productPropertyDto.getProductPropertyName()); // Edit property name
//
//            List<ProductPropertyValueDto> newProductPropertyValueDtoList =
//                    productPropertyDto.getProductPropertyValueDtoList();
//            List<ProductPropertyValue> newProductPropertyValueList = new ArrayList<>();
//
//            for (ProductPropertyValueDto productPropertyValueDto : newProductPropertyValueDtoList) { // Create list property value
//                ProductPropertyValue productPropertyValue = new ProductPropertyValue(productPropertyValueDto);
//                newProductPropertyValueList.add(productPropertyValue);
//            }
//
//            List<ProductPropertyValue> existProductPropertyValueInPropertyList =  // Get list exist property value
//                    existingProperty.getProductPropertyValueList();
//
//            for (ProductPropertyValue existProductPropertyValue : existProductPropertyValueInPropertyList) {
//                for (ProductPropertyValue newProductPropertyValue : newProductPropertyValueList) {
//                    if (newProductPropertyValue.getId() == null) {                  // Create list property value
//                        productPropertyValueRepository.save(newProductPropertyValue);
//                        existProductPropertyValueInPropertyList.add(newProductPropertyValue);
//                        existingProperty.setProductPropertyValueList(existProductPropertyValueInPropertyList);
//                        productPropertyRepository.save(existingProperty);
//                    } else {
//                        if (!existProductPropertyValue.getId()                        // Update property value
//                                .equals(newProductPropertyValue.getId())) {
//                            existProductPropertyValue = new ProductPropertyValue().update(newProductPropertyValue);
//                            existProductPropertyValueInPropertyList.add(existProductPropertyValue);
//                            existingProperty.setProductPropertyValueList(existProductPropertyValueInPropertyList);
//                            productPropertyRepository.save(existingProperty);
//                        }
//                    }
//                }
//            }
//        }
//    }


}
