package _Drive.example._Drive.Entities;

import jakarta.persistence.*;
import lombok.*;

import org.locationtech.jts.geom.Point;

@Entity
@Getter
@Setter
@Builder
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private  Double rating;
    private Boolean available;
    private String vehicalId;

    @Column(columnDefinition = "Geometry(Point,4326)")
    private Point currentLocation;

}
