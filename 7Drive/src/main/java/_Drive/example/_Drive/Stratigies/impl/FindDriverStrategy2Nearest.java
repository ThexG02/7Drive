package _Drive.example._Drive.Stratigies.impl;

import _Drive.example._Drive.Dto.RideRequestDto;
import _Drive.example._Drive.Entities.Driver;
import _Drive.example._Drive.Entities.RideRequest;
import _Drive.example._Drive.Repository.DriverRepository;
import _Drive.example._Drive.Stratigies.FindDriver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class FindDriverStrategy2Nearest implements FindDriver {

    private final DriverRepository driverRepository;
    @Override
    public List<Driver> findDriver(RideRequest rideRequest) {
        return driverRepository.findTenNearestDrivers(rideRequest.getPickupLocation());
    }
}
