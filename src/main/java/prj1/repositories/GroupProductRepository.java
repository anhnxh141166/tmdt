package prj1.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import prj1.domains.GroupProduct;
import prj1.domains.GroupProductDetail;

import java.util.List;

@Repository
public interface GroupProductRepository extends MongoRepository<GroupProduct, String> {

    List<GroupProduct> findAllByGroupProductDetailsIn(List<GroupProductDetail> groupProductDetails);

    List<GroupProduct> findAllByIdIn(List<String> ids);



}
