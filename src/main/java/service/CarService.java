package service;

import DAO.CarDao;
import model.Car;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class CarService {

    private static CarService carService;

    private SessionFactory sessionFactory;

    private CarService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService(DBHelper.getSessionFactory());
        }
        return carService;
    }

    public boolean add(Car car) {
        return new CarDao(sessionFactory.openSession()).add(car);
    }

    public Car get(long id) {
        return new CarDao(sessionFactory.openSession()).get(id);
    }

    public Car get(String brand, String model, String licensePlate) {
        return new CarDao(sessionFactory.openSession()).get(brand, model, licensePlate);
    }

    public List<Car> getAll() {
        return new CarDao(sessionFactory.openSession()).getAll();
    }

    public boolean delete(Car car) {
        return new CarDao(sessionFactory.openSession()).delete(car);
    }

    public boolean deleteById(long id) {
        return new CarDao(sessionFactory.openSession()).deleteById(id);
    }

    public void deleteAll() {
        new CarDao(sessionFactory.openSession()).deleteAll();
    }

    public long count() {
        return new CarDao(sessionFactory.openSession()).count();
    }

    public long countByBrand(String brand) {
        return new CarDao(sessionFactory.openSession()).countByBrand(brand);
    }

    public long countByModel(String model) {
        return new CarDao(sessionFactory.openSession()).countByModel(model);
    }

    public boolean hasByLicensePlate(String licensePlate) {
        return new CarDao(sessionFactory.openSession()).hasByLicensePlate(licensePlate);
    }
}