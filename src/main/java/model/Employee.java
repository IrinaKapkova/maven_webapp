package model;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Employee {
    private int id;
    private String first_name;
    private String last_name;
    private String gender;
    private int age;
    private City city;



    public static Employee create (ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setFirst_name(resultSet.getString("first_name"));
        employee.setLast_name(resultSet.getString("last_name"));
        employee.setGender(resultSet.getString("gender"));
        employee.setAge(resultSet.getInt("age"));
        employee.setCity(new City(resultSet.getInt("city_id"), resultSet.getString("city_name")));
        return employee;
    }


}