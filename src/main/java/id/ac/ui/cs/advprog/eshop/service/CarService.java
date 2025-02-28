package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Car;
import java.util.List;

public interface CarService {
    public Car create(Car car);
    public List<Car> findAll();
    Car findById(String carID);
    public void update(String carID, Car car);
    public void deleteCarById(String carID);
}
