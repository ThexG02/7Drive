package _Drive.example._Drive.Service.impl;

import _Drive.example._Drive.Dto.DriverDto;
import _Drive.example._Drive.Dto.RideDto;
import _Drive.example._Drive.Dto.RiderDto;
import _Drive.example._Drive.Entities.Driver;
import _Drive.example._Drive.Entities.Enums.RideRequestStatus;
import _Drive.example._Drive.Entities.Enums.RideStatus;
import _Drive.example._Drive.Entities.Ride;
import _Drive.example._Drive.Entities.RideRequest;
import _Drive.example._Drive.Exceptions.ResourceNotFoundException;
import _Drive.example._Drive.Repository.DriverRepository;
import _Drive.example._Drive.Service.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@RequiredArgsConstructor
@Service
public class DriverServiceimpl implements DriverService {
    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;
    private final PaymentService paymentService;


    @Override
    public RideDto acceptRide(Long rideRequestId) {
        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);
        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.pending)){
            throw new RuntimeException("RideRequest cannot be accepted , status is "+ rideRequest.getRideRequestStatus());
        }
        Driver currentDriver = getCurrentDriver();
        if(!currentDriver.getAvailable()){
            throw  new RuntimeException("Driver cannot accept ride due to unavailability ");
        }

        Driver savedDriver = updateAvailability(currentDriver , false);
        Ride ride = rideService.createNewRide(rideRequest, savedDriver);

        return modelMapper.map(ride, RideDto.class);
    }

    private Driver updateAvailability(Driver driver, boolean available) {
        driver.setAvailable(available);
        return driverRepository.save(driver);
    }
    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(2L).orElseThrow(()-> new ResourceNotFoundException("Driver not found with "+
            "id"+2    ));
    }


    @Override
    public RideDto startride(Long rideRequestId, String otp) {
        Ride ride =rideService.getRideById(rideRequestId);
        Driver driver = getCurrentDriver();

        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver cannot start a ride as he has not accepted it earlier");
        }
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride Status is not confirmed hence cannot be started , status :"+ride.getRideStatus());
        }
        if(!otp.equals(ride.getOtp())){
            throw new RuntimeException("otp is not valid , otp:"+otp);
        }
        ride.setStartAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);

        paymentService.createNewPayment(savedRide);
        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    public RideDto cancelride(Long rideId) {

        Ride ride = rideService.getRideById(rideId);

        Driver driver = getCurrentDriver();
        if(!driver.equals(ride.getDriver())) {
            throw new RuntimeException("Driver cannot start a ride as he has not accepted it earlier");
        }

        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
            throw new RuntimeException("Ride cannot be cancelled, invalid status: "+ride.getRideStatus());
        }

        rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        updateDriverAvailability(driver, true);

        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public Driver updateDriverAvailability(Driver driver, boolean available) {
        driver.setAvailable(available);
        return driverRepository.save(driver);
    }


    @Override
    public RideDto endride(Long rideid) {
        Ride ride = rideService.getRideById(rideid);
        Driver driver = getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw  new RuntimeException("Driver cannot start a ride as he has not accepted it earlier");
        }
        if(!ride.getRideStatus().equals(RideStatus.ONGOING)){
            throw new RuntimeException("Ride has Ended"+ ride.getRideStatus());
        }
        ride.setEndAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ended);
        updateDriverAvailability(driver , true);
        paymentService.processPayment(ride);
        return modelMapper.map(savedRide , RideDto.class);
    }

    @Override
    public RiderDto raterider(Long Riderid, Double rating) {
        return null;
    }

    @Override
    public DriverDto getmyprofile() {
        Driver currentDriver = getCurrentDriver();

        return modelMapper.map(currentDriver, DriverDto.class);
    }

    @Override
    public Page<RideDto> getallrides(PageRequest pageRequest) {
        Driver currentDriver = getCurrentDriver();
        return rideService.getAllRidesOfDriver(currentDriver,pageRequest).map(
                ride-> modelMapper.map(ride, RideDto.class)
        );
    }


}
