package _Drive.example._Drive.Service;

import _Drive.example._Drive.Dto.DriverDto;
import _Drive.example._Drive.Dto.RideDto;
import _Drive.example._Drive.Dto.RiderDto;
import _Drive.example._Drive.Entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface DriverService {
    //ride related services
    RideDto acceptRide(Long rideRequestId);
    RideDto startride(Long rideRequestId, String otp);

    RideDto cancelride(Long rideid);

    RideDto endride(Long rideid);

    //Rating service for the driver to rate the drive
    RiderDto raterider(Long Riderid , Double  rating);

    //profile service for the driver
    DriverDto getmyprofile();

    //service for list of available rides
     Page<RideDto> getallrides(PageRequest pageRequest);

     Driver getCurrentDriver();

     Driver updateDriverAvailability(Driver driver , boolean available);



}
