package DAO;

import entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    void add (Employee employee);
    Employee getById (int id);
    List<Employee> readAll();
    void updateEmployee (Employee employee);
    void deleteEmployee (Employee employee);
    void deleteEmployeeById(int id);
    void updateEmployeeById(int id, Employee employee);
}
