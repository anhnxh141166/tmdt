package prj1.repositories;

import org.modelmapper.internal.bytebuddy.dynamic.DynamicType;
import org.springframework.data.mongodb.repository.MongoRepository;
import prj1.domains.Shop;

import java.util.List;

public interface ShopRepository extends MongoRepository<Shop , String>{
//    List<Shop> findByNameContainingIgnoreCase(String keyword);
}
