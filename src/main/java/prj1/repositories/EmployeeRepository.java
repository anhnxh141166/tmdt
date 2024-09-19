package prj1.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import prj1.domains.Employee;
@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Long>{

}
