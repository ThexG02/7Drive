package _Drive.example._Drive.Stratigies.impl;

import _Drive.example._Drive.Entities.RideRequest;
import _Drive.example._Drive.Service.DistanceService;
import _Drive.example._Drive.Stratigies.CalculateFare;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculateFairStrategy2Default implements CalculateFare {

    private final DistanceService distanceService;
    @Override
    public Double CalFair(RideRequest rideRequest) {
    Double distance = distanceService.calcDistance(rideRequest.getPickupLocation(),
            rideRequest.getDropoffloaction());

        return distance*Ride_Fare_Multiplier;
    }
}
