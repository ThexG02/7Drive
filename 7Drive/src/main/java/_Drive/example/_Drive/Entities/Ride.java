package _Drive.example._Drive.Entities;

import _Drive.example._Drive.Entities.Enums.PaymentMethod;
import _Drive.example._Drive.Entities.Enums.RideStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import org.locationtech.jts.geom.Point;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "Geometry(Point,4326)")
    private Point pickupLocation;

    @Column(columnDefinition = "Geometry(Point,4326)")
    private Point dropoffloaction;

    @CreationTimestamp
    private LocalDateTime ridecreatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    private Rider rider;

   @Enumerated(EnumType.STRING)
   private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

    private LocalDateTime StartAt;
    private LocalDateTime EndAt;
    private String otp;
    private Double fare;

    public void setId(Long o) {

    }
}
