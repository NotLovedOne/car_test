package org.example.car_test.service;

import org.example.car_test.model.Car;
import org.example.car_test.repository.CarRepository;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(UUID id) {
        return carRepository.findById(id).orElseThrow(() -> new ExpressionException("Car not found"));
    }

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(UUID id, Car car) {
        Car existingCar = getCarById(id);
        existingCar.setMake(car.getMake());
        existingCar.setModel(car.getModel());
        existingCar.setYear(car.getYear());
        existingCar.setPrice(car.getPrice());
        existingCar.setVin(car.getVin());
        return carRepository.save(existingCar);
    }

    public void deleteCar(UUID id) {
        carRepository.deleteById(id);
    }
}
