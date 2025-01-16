package _Drive.example._Drive.Stratigies;

import _Drive.example._Drive.Dto.RideRequestDto;
import _Drive.example._Drive.Entities.Driver;
import _Drive.example._Drive.Entities.RideRequest;

import java.util.List;

public interface FindDriver {
    List<Driver> findDriver(RideRequest rideRequest);
}
