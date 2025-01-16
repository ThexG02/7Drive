package _Drive.example._Drive.Dto;

import _Drive.example._Drive.Entities.Driver;
import _Drive.example._Drive.Entities.Enums.PaymentMethod;
import _Drive.example._Drive.Entities.Enums.RideStatus;
import _Drive.example._Drive.Entities.Rider;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;


import java.time.LocalDateTime;

public class RideDto {
    private long id;

    private Point PickupLocation;

    private Point Dropoffloaction;

    private LocalDateTime RidecreatedAt;

    private Driver driver;

    private Rider rider;

    private PaymentMethod paymentMethod;

    private RideStatus rideStatus;

    private LocalDateTime StartAt;
    private LocalDateTime EndAt;
    private String otp;
    private Double fare;
}
