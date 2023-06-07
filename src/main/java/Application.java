import entity.City;
import entity.Employee;
import DAO.EmployeeDAO;
import DAO.CityDAO;
import DAO.impl.EmployeeDAOimpl;
import DAO.impl.CityDAOimpl;

public class Application {
    public static void main(String[] args) {
        //Создаем объекты классов EmployeeDAOImpl и CityDAOimpl
        EmployeeDAO employeeDAO = new EmployeeDAOimpl();
        CityDAO cityDAO = new CityDAOimpl();
        // Создаём новые экземпляры (строки) в базе данных
        City city = cityDAO.getCityById(2);
        Employee employee1 = new Employee("Denis", "Slonov", "man", 54, city);
        employeeDAO.add(employee1);
        //Вызываем метод удаления из базы данных
        Employee byId = employeeDAO.getById(23);
        employeeDAO.deleteEmployee(byId);
        employeeDAO.readAll().forEach(System.out::println);
        //Проверим метод  обновления данных в базе
        Employee byId1 = employeeDAO.getById(18);
        byId1.setFirstName("Sergey");
        employeeDAO.updateEmployee(byId1);
        System.out.println(employeeDAO.getById(18));
        //Проверим метод удаления из базы данных по id
        employeeDAO.deleteEmployeeById(11);
        //Проверим метод добавления города
        cityDAO.add(new City("Yaroslavl"));
        cityDAO.deleteCity(cityDAO.getCityById(7));
        // Проверим метод для получения всех элементов таблицы
        cityDAO.readAll().forEach(System.out::println);
        employeeDAO.readAll().forEach(System.out::println);
    }
}

