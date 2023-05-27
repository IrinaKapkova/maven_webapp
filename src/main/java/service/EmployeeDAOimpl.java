package service;

import model.City;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOimpl implements EmployeeDAO {
    private final Connection connection;

    public EmployeeDAOimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Employee employee) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO employee (first_name, last_name, gender, age, city_id) VALUES ((?), (?), (?), (?), (?))")) {
            statement.setString(1, employee.getFirst_name());
            statement.setString(2, employee.getLast_name());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCity().getCity_id());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee getById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM employee INNER JOIN city ON employee.city_id=city.city_id WHERE id=(?)")) {
            preparedStatement.setInt(1, id);
            preparedStatement.setMaxRows(1);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return Employee.create(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Employee> readAll() {
        List<Employee> employeeList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM employee INNER JOIN city  ON employee.city_id = city.city_id")) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = Integer.parseInt(resultSet.getString("id"));
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                City city = new City(resultSet.getInt("city_id"),
                        resultSet.getString("city_name"));
                employeeList.add(new Employee(id, first_name, last_name, gender, age, city));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public void updateNameById(int id, String first_name) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE employee SET first_name=(?) WHERE id=(?)")) {

            preparedStatement.setString(1, first_name);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void updateEmployee(int id, Employee employee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE employee SET first_name = (?),last_name=(?), gender= (?), age= (?), city_id= (?) WHERE id=(?)")) {
            preparedStatement.setString(1, employee.getFirst_name());
            preparedStatement.setString(2, employee.getLast_name());
            preparedStatement.setString(3, employee.getGender());
            preparedStatement.setInt(4, employee.getAge());
            preparedStatement.setInt(5, employee.getCity().getCity_id());
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEmployee(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE from employee Where id=(?)")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
