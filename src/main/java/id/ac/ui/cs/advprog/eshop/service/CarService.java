package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Car;
import java.util.List;

public interface CarService {
    Car create(Car car);
    List<Car> findAll();
    Car findById(String carID);
    void update(String carID, Car car);
    void deleteCarById(String carID);
}
