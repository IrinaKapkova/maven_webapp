package DAO.impl;

import DAO.CityDAO;
import entity.City;
import java.util.List;
import exception.NotFoundEmployeeException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtils;


public class CityDAOimpl implements CityDAO {


    @Override
    public void add(City city) {
        Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        try (session) {
            Transaction transaction = session.beginTransaction();
            session.save(city);
            transaction.commit();
        }
    }

    @Override
    public City getCityById(int id) throws NotFoundEmployeeException {
        try (Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession()) {
            City city = session.get(City.class, id);
            if (city != null) {
                return city;
            } else {
                throw new NotFoundEmployeeException("Город не найден в базе данных");
            }
        }
    }


    @Override
    public List<City> readAll() {
        try (Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession()) {
            List<City> cityList = session.createQuery("FROM City").list();
            if (!cityList.isEmpty()) {
                return cityList;
            } else {
                throw new NotFoundEmployeeException("Ни один город не найден в базе данных");
            }
        }
    }



    @Override
    public void updateCity(City city) {
        try (Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(city);
            transaction.commit();
        }
    }

    @Override
    public void deleteCityById(int id) {

        try (Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
//            Query query = (Query) session.createQuery("DELETE FROM City WHERE id =:id");
//            query.setParameter("id" , id);
//            query.executeUpDate();
            City city1 = session.get(City.class, id);
            session.delete(city1);
            transaction.commit();
        }
    }

    @Override
    public void updateCityById(int id, City city)  {
        try (Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            City city2 = session.get(City.class, id);
            session.update(city2);
            transaction.commit();
        }
    }

    @Override
    public void deleteCity(City city) {
        try (Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(city);
            transaction.commit();
        }
    }
}


