package DAO;

import entity.City;
import exception.NotFoundEmployeeException;

import java.util.List;

public interface CityDAO {
    void add (City city);
    City getCityById (int id) throws NotFoundEmployeeException;
    List<City> readAll();
    void updateCity (City city);
    void deleteCity (City city);
    void deleteCityById(int id);
    void updateCityById(int id, City city);

}
