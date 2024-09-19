package prj1.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import prj1.domains.Category;
import prj1.domains.ProductProperty;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {
    Page<Category> findAll(Pageable pageable);
}
