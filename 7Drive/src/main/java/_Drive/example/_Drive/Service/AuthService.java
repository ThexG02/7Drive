package _Drive.example._Drive.Service;

import _Drive.example._Drive.Dto.DriverDto;
import _Drive.example._Drive.Dto.SignupDto;
import _Drive.example._Drive.Dto.UserDto;

public interface AuthService {

    String[] login(String email, String password);

    UserDto signup(SignupDto signupDto);

    DriverDto onboardNewDriver(Long userId, String vehicleId);

    String refreshToken(String refreshToken);
}
