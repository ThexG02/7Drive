package _Drive.example._Drive.Service;

import _Drive.example._Drive.Dto.DriverDto;
import _Drive.example._Drive.Dto.RiderDto;
import _Drive.example._Drive.Entities.Ride;


public interface RatingService {

    DriverDto rateDriver(Ride ride, Double rating);
    RiderDto rateRider(Ride ride, Double rating);

    void createNewRating(Ride ride);
}
