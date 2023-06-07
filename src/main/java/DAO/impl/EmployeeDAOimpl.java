package DAO.impl;

import DAO.EmployeeDAO;
import exception.NotFoundEmployeeException;
import entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtils;

import java.util.List;

public class EmployeeDAOimpl implements EmployeeDAO {


    @Override
    public void add(Employee employee) {
        Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        try (session) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
    }

    @Override
    public Employee getById(int id) {
        try (Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession()) {
            Employee employee = session.get(Employee.class, id);
            if (employee != null) {
                return employee;
            } else {
                throw new NotFoundEmployeeException("Работник не найден в базе данных");
            }
        }
    }


    @Override
    public List<Employee> readAll() {
        try (Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession()) {
            List<Employee> employeeList = session.createQuery("FROM Employee").list();
            if (!employeeList.isEmpty()) {
                return employeeList;
            } else {
                throw new NotFoundEmployeeException("Список работников не найден в базе данных");
            }
        }
    }



    @Override
    public void updateEmployee(Employee employee) {
        try (Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        }
    }

    @Override
    public void deleteEmployeeById(int id) {

        try (Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
//            Query query = (Query) session.createQuery("DELETE FROM Employee WHERE id =:id");
//            query.setParameter("id" , id);
//            query.executeUpDate();
            Employee employee1 = session.get(Employee.class, id);
            session.delete(employee1);
            transaction.commit();
        }
    }

    @Override
    public void updateEmployeeById(int id, Employee employee)  {
        try (Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee2 = session.get(Employee.class, id);
            session.update(employee2);
            transaction.commit();
        }
    }

    @Override
    public void deleteEmployee(Employee employee) {
        try (Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }
    }
}
