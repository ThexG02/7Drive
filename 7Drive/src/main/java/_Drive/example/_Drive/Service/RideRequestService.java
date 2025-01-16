package _Drive.example._Drive.Service;

import _Drive.example._Drive.Entities.Ride;
import _Drive.example._Drive.Entities.RideRequest;

public interface RideRequestService {
    RideRequest findRideRequestById(Long rideRequestId);
    void update (RideRequest rideRequest);
}
