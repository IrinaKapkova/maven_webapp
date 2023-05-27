import model.City;
import model.Employee;
import service.EmployeeDAOimpl;

import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException {
        // Определяем переменные для подключения к базе данных
        final String url = "jdbc:postgresql://localhost:5432/skypro";
        final String user = "postgres";
        final String password = "IrNiKa";

        // Создаем соединение с базой данных
        Connection connection = DriverManager.getConnection(url, user, password);
        //Создаем объект класса EmployeeDAOImpl
        EmployeeDAOimpl employeeDAOimpl = new EmployeeDAOimpl(connection);
        // Создаём новый экземпляр (строку) в базе данных
        City city1 = new City(3, "Rostov");
        Employee employee = new Employee(1, "Marina", "Vasina", "woman", 32, city1);
        employeeDAOimpl.add(employee);
        //Вызываем метод удаления из базы данных
        employeeDAOimpl.deleteEmployee(13);
//        Проверим работы метода обновления данных в базе
        Employee employee1 = new Employee(14, "Oleg", "Kotov", "man", 25, city1);
        employeeDAOimpl.updateEmployee(11, employee1);
        employeeDAOimpl.updateNameById(9, "Raya");
        System.out.println(employeeDAOimpl.getById(11));
// вызова метода для получения всех элементов таблицы
        System.out.println(employeeDAOimpl.readAll());
    }
}

