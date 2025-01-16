package _Drive.example._Drive.Service.impl;

import _Drive.example._Drive.Dto.RideRequestDto;
import _Drive.example._Drive.Entities.Driver;
import _Drive.example._Drive.Entities.Enums.RideRequestStatus;
import _Drive.example._Drive.Entities.Enums.RideStatus;
import _Drive.example._Drive.Entities.Ride;
import _Drive.example._Drive.Entities.RideRequest;
import _Drive.example._Drive.Entities.Rider;
import _Drive.example._Drive.Exceptions.ResourceNotFoundException;
import _Drive.example._Drive.Repository.RideRepository;
import _Drive.example._Drive.Service.RideRequestService;
import _Drive.example._Drive.Service.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class RideServiceimpl implements RideService {
    private final RideRepository rideRepository;
    private final RideRequestService rideRequestService;
    private final ModelMapper modelMapper;
    @Override
    public Rider getRideById(Long rideId) {
         return rideRepository.findById(rideId)
                 .orElseThrow(()-> new ResourceNotFoundException("ride not found with id"+rideId)).getRider();
    }

    @Override
    public void matchWithDrivers(RideRequestDto rideRequestDto) {

    }

    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {
        rideRequest.setRideRequestStatus(RideRequestStatus.Confirmed);
        Ride ride = modelMapper.map(rideRequest,Ride.class);
        ride.setRideStatus(RideStatus.Confirmed);
        ride.setDriver(driver);
        ride.setOtp(generateRandomOTP());
        ride.setId(null);

        rideRequestService.update(rideRequest);
        return rideRepository.save(ride);
    }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
        ride.setRideStatus(rideStatus);
        return rideRepository.save(ride);
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Driver driverId, PageRequest pageRequest) {
        return null;
    }
    private String generateRandomOTP() {
        Random random = new Random();
        int otpInt = random.nextInt(10000);  //0 to 9999
        return String.format("%04d", otpInt);
    }
}
