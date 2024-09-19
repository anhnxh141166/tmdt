package prj1.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import prj1.domains.ProductProperty;

import java.util.List;

@Repository
public interface ProductPropertyRepository extends MongoRepository<ProductProperty, String> {
    @Query("{'categoryList.$id' :  ?0}")
    List<ProductProperty> findProductPropertiesByCategoryId(String categoryId);

//    ProductProperty
}
