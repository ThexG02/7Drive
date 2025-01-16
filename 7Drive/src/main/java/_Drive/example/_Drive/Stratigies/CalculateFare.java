package _Drive.example._Drive.Stratigies;

import _Drive.example._Drive.Dto.RideRequestDto;
import _Drive.example._Drive.Entities.RideRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



public interface CalculateFare {

 double Ride_Fare_Multiplier =10;


    Double CalFair(RideRequest rideRequest);
}
