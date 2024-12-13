package org.example.car_test.service;

import org.example.car_test.model.Car;
import org.example.car_test.repository.CarRepository;
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
        List<Car> cars = carRepository.findAll();
        if (cars.isEmpty()) {
            throw new IllegalStateException("машин нету).");
        }
        return cars;
    }

    public Car getCarById(UUID id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("машина с таким айди не найден: " + id));
    }

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(UUID id, Car car) {
        Car existingCar = getCarById(id);

        if (!existingCar.getVin().equals(car.getVin()) &&
                carRepository.findAll().stream().anyMatch(c -> c.getVin().equals(car.getVin()))) {
            throw new IllegalArgumentException("машина с вин " + car.getVin() + " уже существует.");
        }

        existingCar.setMake(car.getMake());
        existingCar.setModel(car.getModel());
        existingCar.setYear(car.getYear());
        existingCar.setPrice(car.getPrice());
        existingCar.setVin(car.getVin());
        return carRepository.save(existingCar);
    }

    public void deleteCar(UUID id) {
        if (!carRepository.existsById(id)) {
            throw new IllegalArgumentException("машина с айди " + id + " не существует.");
        }
        carRepository.deleteById(id);
    }
}

