package prj1.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import prj1.domains.SaleProperty;

import java.util.List;
import java.util.Set;

@Repository
public interface SalePropertyRepository extends MongoRepository<SaleProperty, String> {
    List<SaleProperty> findAllByGroupProductDetailIdIsIn(Set<String> ids);
}
