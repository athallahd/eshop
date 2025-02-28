package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Car;
import java.util.Iterator;

public interface CarRepository extends Create<Car>, Delete, Find<Car>, Update<Car> {
    Car create(Car car);
    Iterator<Car> findAll();
    Car findById(String id);
    Car update(String id, Car car);
    void delete(String id);
}