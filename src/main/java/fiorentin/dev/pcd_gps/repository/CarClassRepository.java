package fiorentin.dev.pcd_gps.repository;

import fiorentin.dev.pcd_gps.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarClassRepository extends JpaRepository<Car, Long> {
}
