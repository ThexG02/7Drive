package _Drive.example._Drive.Service;

import _Drive.example._Drive.Dto.DriverDto;
import _Drive.example._Drive.Dto.RideDto;
import _Drive.example._Drive.Dto.RideRequestDto;
import _Drive.example._Drive.Dto.RiderDto;
import _Drive.example._Drive.Entities.Ride;
import _Drive.example._Drive.Entities.Rider;
import _Drive.example._Drive.Entities.User;

import java.util.List;

public interface RiderService {

    //ride request service
    RideRequestDto requestride(RideRequestDto rideRequestDto);

    //ride related service
    RideDto cancleride(Long rideid);

    // driver rating service
    DriverDto ratedriver (Long driverid, Double rating);

    //profile servies
    RiderDto getMyProfile();

    //previous rides list
    List<RideDto> getAllMyRides();

    //creation of the NEW USER
    Rider createNewRider(User user);

    //get current rider data
    Rider getcurrntRider();
}
