package DAO;

import model.Car;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class CarDao extends AbstractDao {

    public CarDao(Session session) {
        super(session);
    }

    public boolean add(Car car) {
        return runInSession(session ->
                (Long) session.save(car) >= 0
        );
    }

    public Car get(long id) {
        return runInSession(session ->
                (Car) session.get(Car.class, id)
        );
    }

    public Car get(String brand, String model, String licensePlate) {
        return runInSession(session -> {
            Query query = session.createQuery("from Car where brand = :brand and model = :model and licensePlate = :licensePlate");
            query.setParameter("brand", brand);
            query.setParameter("model", model);
            query.setParameter("licensePlate", licensePlate);
            return (Car) query.uniqueResult();
        });
    }

    @SuppressWarnings("unchecked")
    public List<Car> getAll() {
        return runInSession(session ->
                (List<Car>) session.createQuery("from Car").list()
        );
    }

    public boolean delete(Car car) {
        return runInSession(session -> {
            Query query = session.createQuery("delete from Car where id = :id");
            query.setParameter("id", car.getId());

            return query.executeUpdate() > 0;
        });
    }

    public boolean deleteById(long id) {
        return runInSession(session -> {
            Query query = session.createQuery("delete from Car where id = :id");
            query.setParameter("id", id);
            return query.executeUpdate() > 0;
        });
    }

    public void deleteAll() {
        runInSession(session -> {
            Query query = session.createQuery("delete from Car");
            query.executeUpdate();
        });
    }

    public long count() {
        return runInSession(session -> {
            Query query = session.createQuery("select count(id) from Car");
            return (long) query.uniqueResult();
        });
    }

    public long countByBrand(String brand) {
        return runInSession(session -> {
            Query query = session.createQuery("select count(*) from Car where brand = :brand");
            query.setParameter("brand", brand);

            return (long) query.uniqueResult();
        });
    }

    public long countByModel(String model) {
        return runInSession(session -> {
            Query query = session.createQuery("select count(*) from Car where model= :model");
            query.setParameter("model", model);

            return (long) query.uniqueResult();
        });
    }

    public boolean hasByLicensePlate(String licensePlate) {
        return runInSession(session -> {
            Query query = session.createQuery("from Car where licensePlate = :licensePlate");
            query.setParameter("licensePlate", licensePlate);
            return query.uniqueResult() != null;
        });
    }
}