package prj1.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import prj1.domains.Variation;

import java.util.List;
import java.util.Set;

@Repository
public interface VariationRepository extends MongoRepository<Variation, String> {
    List<Variation> findAllByIdIn(Set<String> ids);
}
