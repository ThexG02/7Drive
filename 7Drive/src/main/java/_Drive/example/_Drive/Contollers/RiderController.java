package _Drive.example._Drive.Contollers;

import _Drive.example._Drive.Dto.RideRequestDto;
import _Drive.example._Drive.Service.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rider")
@RequiredArgsConstructor
public class RiderController {
    private final RiderService riderService;
    @PostMapping("/riderequest")
    public ResponseEntity<RideRequestDto> riderequest(@RequestBody RideRequestDto rideRequestDto){
        return  ResponseEntity.ok(riderService.requestride(rideRequestDto));
    }
}
