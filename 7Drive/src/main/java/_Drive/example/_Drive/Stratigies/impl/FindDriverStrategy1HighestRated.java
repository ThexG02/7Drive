package _Drive.example._Drive.Stratigies.impl;

import _Drive.example._Drive.Dto.RideRequestDto;
import _Drive.example._Drive.Entities.Driver;
import _Drive.example._Drive.Entities.RideRequest;
import _Drive.example._Drive.Repository.DriverRepository;
import _Drive.example._Drive.Stratigies.FindDriver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class FindDriverStrategy1HighestRated implements FindDriver {

    private final DriverRepository driverRepository;
    @Override
    public List<Driver> findDriver(RideRequest rideRequestDto) {
        return driverRepository.findTenTopRatedNearbyDrivers(rideRequestDto.getPickupLocation());
    }
}
