package prj1.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prj1.domains.Product;
import prj1.domains.Promotion;

import java.util.List;
import java.util.Optional;

@Repository
public interface PromotionRepository extends MongoRepository<Promotion, String> {

    Optional<Promotion> findPromotionById(String id);

    Optional<Promotion> findPromotionByName(String name);

    @Query("{$or: [{$and: [{status: {$eq: ?0}}]}, {$or: [{name: {$regex: ?1, $options: 'i'}}, {name: {$exists: false}}]}]}")
    Page<Promotion> getAllPromotion(@Param("status") Integer status, @Param("search") String search, Pageable pageable);

//    @Query("{$or: [{$and: [{status: {$eq: ?0}}]}, {name: {$exists: false}}]}")
//    Page<Promotion> getAllPromotion(@Param("status") Integer status, @Param("search") String search, Pageable pageable);


}
