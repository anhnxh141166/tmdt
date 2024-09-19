package prj1.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import prj1.domains.Brand;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends MongoRepository<Brand, String>  {
    Optional<Brand> findByName(String name);
    List<Brand> findByNameContainingIgnoreCase(String keyword);


}

