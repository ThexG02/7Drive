package _Drive.example._Drive.Service.impl;

import _Drive.example._Drive.Dto.DriverDto;
import _Drive.example._Drive.Dto.RideDto;
import _Drive.example._Drive.Dto.RideRequestDto;
import _Drive.example._Drive.Dto.RiderDto;
import _Drive.example._Drive.Entities.Enums.RideRequestStatus;
import _Drive.example._Drive.Entities.Ride;
import _Drive.example._Drive.Entities.RideRequest;
import _Drive.example._Drive.Entities.Rider;
import _Drive.example._Drive.Entities.User;
import _Drive.example._Drive.Exceptions.ResourceNotFoundException;
import _Drive.example._Drive.Repository.RideRequestRepository;
import _Drive.example._Drive.Repository.RiderRepository;
import _Drive.example._Drive.Service.RiderService;
import _Drive.example._Drive.Stratigies.CalculateFare;
import _Drive.example._Drive.Stratigies.FindDriver;
import _Drive.example._Drive.Stratigies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
        return null;
    }

    @Override
    public DriverDto ratedriver(Long driverid, Double rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
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
        // TODO : implement Spring Security
        return riderRepository.findById(1L).orElseThrow(()-> new ResourceNotFoundException(
                "Rider with id :"+1+"+is not found"
        ));
    }
}
