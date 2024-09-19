package prj1.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import prj1.domains.GroupProductDetail;

import java.util.List;

@Repository
public interface GroupProductDetailRepository extends MongoRepository<GroupProductDetail, String> {
    List<GroupProductDetail> findAllByProductIdAndStatus(String productId, Integer status);

    List<GroupProductDetail> findAllByProductId(String productId);

}
