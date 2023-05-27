package service;

import model.Employee;

import java.awt.*;
import java.util.List;

public interface EmployeeDAO {
    void add (Employee employee);
    Employee getById (int id);
    List<Employee> readAll();
    void updateNameById (int id,String first_name);
    void updateEmployee (int id, Employee employee);
    void deleteEmployee (int id);

}
