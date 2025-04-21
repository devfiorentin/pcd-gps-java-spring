package fiorentin.dev.pcd_gps.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String carModel;
    @Column(unique = true)
    private String plate;
    private double latitude;
    private double longitude;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public Car(String carModel, String plate, double latitude, double longitude, User user) {
        this.carModel = carModel;
        this.plate = plate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.user = user;
    }


}
