package prj1.services;

import prj1.domains.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    //Ham tao moi nhan vien
    public Employee creatEmployee(Employee employee);

    // ham chinh sua thong tin nhan vien
    public Employee updateEmployee(long id , Employee employee);

    // ham xoa nhan vien
    public void removeEmployee(long id);

    // ham lay ra danh sach nhan vien
    public List<Employee> getAllEmployee();

    //ham lay ra 1 nhan vien
    public Optional<Employee> getOneEmployee(long id);
}
