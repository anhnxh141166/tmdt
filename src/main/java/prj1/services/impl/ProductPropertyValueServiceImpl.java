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
import prj1.domains.ProductPropertyValue;
import prj1.exceptions.CustomException;
import prj1.exceptions.ExceptionUtils;
import prj1.models.productpropertyvalue.ProductPropertyValueDTO;
import prj1.repositories.ProductPropertyValueRepository;
import prj1.services.ProductPropertyValueService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductPropertyValueServiceImpl implements ProductPropertyValueService {
    private final ProductPropertyValueRepository productPropertyValueRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public ProductPropertyValue getPropertyValueById(String id) {
        Optional<ProductPropertyValue> productPropertyValueOptional =
                productPropertyValueRepository.findById(id);
        if(productPropertyValueOptional.isEmpty()){
            throw new CustomException(ExceptionUtils.ITEM_NOT_EXIST);
        }
        return productPropertyValueOptional.get();
    }

    @Override
    public Page<ProductPropertyValueDTO> search(String name, Pageable pageable) {
        Query query = new Query();
        if(name != null && !name.isEmpty()){
            query.addCriteria(Criteria.where("productPropertyValueName").regex(name,"i"));
        }
        query.with(pageable);
        List<ProductPropertyValue> productPropertyValueList =
                mongoTemplate.find(query,ProductPropertyValue.class);
        List<ProductPropertyValueDTO> productPropertyValueDTOList = productPropertyValueList
                .stream()
                .map(productPropertyValue -> new ProductPropertyValueDTO(productPropertyValue))
                .toList();
        long count = mongoTemplate.count(query,ProductPropertyValue.class);
        return new PageImpl<>(productPropertyValueDTOList,pageable,count);
    }

    @Override
    public void createPropertyValue(ProductPropertyValueDTO productPropertyValueDTO) {
        ProductPropertyValue productPropertyValue = new ProductPropertyValue(productPropertyValueDTO);
        productPropertyValueRepository.save(productPropertyValue);
    }

    @Override
    public void updatePropertyValue(ProductPropertyValueDTO productPropertyValueDTO) {
        ProductPropertyValue productPropertyValue = getPropertyValueById(productPropertyValueDTO.getId());
        productPropertyValue.update(productPropertyValueDTO);
        productPropertyValueRepository.save(productPropertyValue);
    }

    @Override
    public void deletePropertyValue(String id) {
        ProductPropertyValue productPropertyValue = getPropertyValueById(id);
        productPropertyValueRepository.delete(productPropertyValue);
    }
}
