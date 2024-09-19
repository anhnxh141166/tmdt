package prj1.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import prj1.domains.ProductPropertyValue;

@Repository
public interface ProductPropertyValueRepository extends MongoRepository<ProductPropertyValue, String> {
}
