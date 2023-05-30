import entity.Employee;
import service.EmployeeDAO;
import service.EmployeeDAOimpl;
public class Application {
    public static void main(String[] args) {
        //Создаем объект класса EmployeeDAOImpl
        EmployeeDAO employeeDAO = new EmployeeDAOimpl();
        // Создаём новый экземпляр (строку) в базе данных
        Employee employee1 = new Employee("Danil", "Petrov", "man", 19, 4);
        employeeDAO.add(employee1);
        employeeDAO.readAll().forEach(System.out::println);
        //Вызываем метод удаления из базы данных
        employeeDAO.deleteEmployee(employee1);
        employeeDAO.readAll().forEach(System.out::println);
//        Проверим метод  обновления данных в базе
        Employee byId = employeeDAO.getById(6);
        byId.setFirst_name("Lina");
        employeeDAO.updateEmployee(byId);
        System.out.println(employeeDAO.getById(6));
// Проверим метод для получения всех элементов таблицы
        employeeDAO.readAll().forEach(System.out::println);
        //Проверим метод удаления из базы данных по id
        employeeDAO.deleteEmployeeById(11);
        employeeDAO.readAll().forEach(System.out::println);
    }
}

