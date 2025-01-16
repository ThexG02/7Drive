package _Drive.example._Drive.Service.impl;

import _Drive.example._Drive.Entities.RideRequest;
import _Drive.example._Drive.Exceptions.ResourceNotFoundException;
import _Drive.example._Drive.Repository.RideRequestRepository;
import _Drive.example._Drive.Service.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;
    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository.findById(rideRequestId)
                .orElseThrow(()-> new ResourceNotFoundException("rideRequest not found with id"+ rideRequestId));
    }

    @Override
    public void update(RideRequest rideRequest) {
        rideRequestRepository.findById(rideRequest.getId())
                .orElseThrow(()-> new ResourceNotFoundException("ride request not found with id :"+rideRequest.getId()));
        rideRequestRepository.save(rideRequest);
    }
}
