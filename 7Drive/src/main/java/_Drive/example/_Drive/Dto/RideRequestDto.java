package _Drive.example._Drive.Dto;

import _Drive.example._Drive.Entities.Enums.PaymentMethod;
import _Drive.example._Drive.Entities.Enums.RideStatus;
import _Drive.example._Drive.Entities.Rider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import org.locationtech.jts.geom.Point;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDto {

    private long id;

    private PointDto pickuplocation;

    private PointDto dropoffloaction;

    private PaymentMethod paymentMethod;

    private LocalDateTime RequestedTime;


    private Rider rider;



    private RideStatus rideStatus;

    private Double fare;

}
