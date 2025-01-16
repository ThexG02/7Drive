package _Drive.example._Drive.Stratigies.impl;

import _Drive.example._Drive.Dto.RideRequestDto;
import _Drive.example._Drive.Entities.RideRequest;
import _Drive.example._Drive.Service.DistanceService;
import _Drive.example._Drive.Stratigies.CalculateFare;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CaluculateFair1Surge implements CalculateFare {

    private final DistanceService distanceService;
    private static final double SURGE_FACTOR =2;

    @Override
    public Double CalFair(RideRequest rideRequestDto) {

        double distance = distanceService.calcDistance(rideRequestDto.getPickupLocation(),rideRequestDto.getDropoffloaction());
        return distance*Ride_Fare_Multiplier*SURGE_FACTOR;
    }
}
