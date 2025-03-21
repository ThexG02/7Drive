package _Drive.example._Drive.Service.impl;

import _Drive.example._Drive.Dto.DriverDto;
import _Drive.example._Drive.Dto.RideDto;
import _Drive.example._Drive.Dto.RideRequestDto;
import _Drive.example._Drive.Dto.RiderDto;
import _Drive.example._Drive.Entities.Enums.RideRequestStatus;
import _Drive.example._Drive.Entities.Enums.RideStatus;
import _Drive.example._Drive.Entities.Ride;
import _Drive.example._Drive.Entities.RideRequest;
import _Drive.example._Drive.Entities.Rider;
import _Drive.example._Drive.Entities.User;
import _Drive.example._Drive.Exceptions.ResourceNotFoundException;
import _Drive.example._Drive.Repository.RideRequestRepository;
import _Drive.example._Drive.Repository.RiderRepository;
import _Drive.example._Drive.Service.DriverService;
import _Drive.example._Drive.Service.RatingService;
import _Drive.example._Drive.Service.RideService;
import _Drive.example._Drive.Service.RiderService;
import _Drive.example._Drive.Stratigies.CalculateFare;
import _Drive.example._Drive.Stratigies.FindDriver;
import _Drive.example._Drive.Stratigies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j

public class RiderServicesimpl implements RiderService {

    private final ModelMapper modelMapper;
   private final RideStrategyManager rideStrategyManager;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final RideService rideService;
    private final DriverService driverService;
    private final RatingService ratingService;
    @Override
    @Transactional
    public RideRequestDto requestride(RideRequestDto rideRequestDto) {
        Rider rider = getcurrntRider();

        //dto to entity conversion
        RideRequest rideRequest = modelMapper.map(rideRequestDto,RideRequest.class);

        rideRequest.setRideRequestStatus(RideRequestStatus.pending);
        rideRequest.setRider(rider);


        //logging the ride request
        log.info(rideRequest.toString());

        //Calculating the fare for the requested ride using calculate fare strategy
        Double fare = rideStrategyManager.calculateFare().CalFair(rideRequest);
        rideRequest.setFare(fare);

        // saving the ride request in the repository
        RideRequest savedRideRequest= rideRequestRepository.save(rideRequest);

        //finding the driver from find driver Strategy
        rideStrategyManager.MatchDriver(rider.getRating()).findDriver(rideRequest);


        return modelMapper.map(savedRideRequest,RideRequestDto.class);
    }

    @Override
    public RideDto cancleride(Long rideid) {

        Rider rider = getcurrntRider();
        Ride ride = rideService.getRideById(rideid);

        if(!rider.equals(ride.getRider())){
            throw new RuntimeException(("Rider does not own this ride with Id:"+rideid));
        }
        if(!ride.getRideStatus().equals(RideStatus .Confirmed)){
            throw new RuntimeException("Ridecanot be cancelled , invalid status:"+ride.getRideStatus());
        }

        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        driverService.updateDriverAvailability(ride.getDriver(),true);

        return modelMapper.map(savedRide , RideDto.class);
    }

    @Override
    public DriverDto ratedriver(Long rideId, Double rating) {
        Ride ride = rideService.getRideById(rideId);
        Rider rider= getcurrntRider();
        if(!rider.equals(ride.getRider())){
            throw new RuntimeException("Rider is not the Part of the Ride");
        }
        if(!ride.getRideStatus().equals(RideStatus.ended)){
            throw new RuntimeException("ride Status is not ended  , please wait till ride end");
        }

        return ratingService.rateDriver(ride,rating);
    }


    @Override
    public RiderDto getMyProfile() {
        Rider currentRider = getcurrntRider();
        return modelMapper.map(currentRider, RiderDto.class);
        }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        Rider currentRider= getcurrntRider();
        return rideService.getAllRidesOfRider(currentRider,pageRequest).map(
                ride -> modelMapper.map(ride, RideDto.class)
        );
    }


    @Override
    public Rider createNewRider(User user) {
        Rider rider= Rider
                .builder()
                .user(user)
                .rating(0.0)
                .build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getcurrntRider() {
       User user =(User) SecurityContextHolder.getContext().getAuthentication();
        return riderRepository.findByUser(user).orElseThrow(()-> new ResourceNotFoundException(
                "Rider not associated with user with id: "+user.getId()
        ));
    }
}
