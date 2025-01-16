package _Drive.example._Drive.Service;

import _Drive.example._Drive.Dto.RideRequestDto;
import _Drive.example._Drive.Entities.Driver;
import _Drive.example._Drive.Entities.Enums.RideStatus;
import _Drive.example._Drive.Entities.Ride;
import _Drive.example._Drive.Entities.RideRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {
    Ride getRideById(Long rideId);

    void matchWithDrivers(RideRequestDto rideRequestDto);

    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Driver driverId, PageRequest pageRequest);
    
}
