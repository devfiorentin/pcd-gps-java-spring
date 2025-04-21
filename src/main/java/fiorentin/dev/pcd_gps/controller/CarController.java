package fiorentin.dev.pcd_gps.controller;

import fiorentin.dev.pcd_gps.controller.dto.LocationDTO;
import fiorentin.dev.pcd_gps.entity.Car;
import fiorentin.dev.pcd_gps.repository.CarClassRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CarController {

    private final CarClassRepository repository;

    public CarController(CarClassRepository repository) {
        this.repository = repository;
    }

    @PutMapping("/car/location/{id}")
    public ResponseEntity<Car> updateLocation(@PathVariable Long id, @RequestBody LocationDTO body) {
        Optional<Car> carOptional = repository.findById(id);
        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            car.setLatitude(body.latitude());
            car.setLongitude(body.longitude());
            repository.save(car);
            return ResponseEntity.ok(car);
        }
        return ResponseEntity.notFound().build();
    }
}
