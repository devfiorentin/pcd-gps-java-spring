package fiorentin.dev.pcd_gps.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class CarInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String carName;
    private String plate;
    private double latitude;
    private double longitude;

    @ManyToOne
    @JoinColumn(name = "user_info")
    @JsonBackReference
    private User user;
}
