package prj1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import prj1.domains.Employee;
import prj1.services.EmployeeService;
import prj1.services.impl.EmployeeServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping("/creat")
    public Employee creatEmployee(@RequestBody Employee employee){
        return employeeService.creatEmployee(employee);
    }

    @PutMapping("/update")
    public Employee update(@RequestParam long id ,@RequestBody Employee employee){
        return employeeService.updateEmployee(id ,employee);
    }

//    @DeleteMapping("/delete")
//    public

    @GetMapping("/list")
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

}
