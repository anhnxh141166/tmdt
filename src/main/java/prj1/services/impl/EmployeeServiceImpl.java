package prj1.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prj1.domains.Employee;
import prj1.repositories.EmployeeRepository;
import prj1.services.EmployeeService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired

    private EmployeeRepository employeeRepository;
    @Override
    public Employee creatEmployee(Employee employee) {
        if (employee != null){
            return employeeRepository.save(employee);
        }
        return null;
    }

    @Override
    public Employee updateEmployee(long id, Employee employee) {
        Optional<Employee> employee1 =employeeRepository.findById(id);
        Employee employee2 ;
        if(employee1.isPresent()){
            employee2 = employee1.get();
            employee2.setName(employee.getName());
            employee2.setPassword(employee.getPassword());
            employee2.setSdt(employee.getSdt());
            employee2.setAddress(employee.getAddress());
            employee2.setEmail(employee.getEmail());
            employee2.setGender(employee.getGender());
            employee2.setUpdate_at(LocalDateTime.now());
            return employeeRepository.save(employee2);
        }
        return null;
    }

    @Override
    public void removeEmployee(long id) {
    Employee employee = employeeRepository.findById(id).orElse(null);
        if(employee != null ) {
            employeeRepository.delete(employee);
        }
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getOneEmployee(long id) {
        return employeeRepository.findById(id);
    }
}
