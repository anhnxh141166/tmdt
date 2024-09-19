package prj1.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import prj1.domains.ProductProperty;
import prj1.models.productproperty.ProductPropertyDTO;

import java.util.List;

public interface ProductPropertyService {

    ProductProperty getProductPropertyById(String id);
    Page<ProductPropertyDTO> search(String name, Pageable pageable);
    void updateProductProperty(ProductPropertyDTO productPropertyDto);
    void createProductProperty(ProductPropertyDTO productPropertyDto);
    void deleteProperty(String id);

    void insertPropertyValueToProperty(String id, ProductPropertyDTO productPropertyDTO);
}
