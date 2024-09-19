package prj1.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import prj1.domains.ProductPropertyValue;
import prj1.models.productproperty.ProductPropertyDTO;
import prj1.models.productpropertyvalue.ProductPropertyValueDTO;

public interface ProductPropertyValueService {

    ProductPropertyValue getPropertyValueById(String id);
    Page<ProductPropertyValueDTO> search(String name, Pageable pageable);

    void createPropertyValue(ProductPropertyValueDTO productPropertyValueDTO);

    void updatePropertyValue(ProductPropertyValueDTO productPropertyValueDTO);

    void deletePropertyValue(String id);
}
