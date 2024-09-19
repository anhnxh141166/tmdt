package prj1.repositories;

//import org.springframework.data.jpa.repository.JpaRepository;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prj1.domains.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    @Query("?#{@qHelper.noBlanks( {name: [0], status: [1], _id: [2] } )}")
    Page<Product> findAll(
            @Param("name") String name,
            @Param("status") Integer status,
            @Param("id") ObjectId id,
            Pageable pageable);


}


