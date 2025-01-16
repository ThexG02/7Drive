package _Drive.example._Drive.Contollers;

import _Drive.example._Drive.Dto.SignupDto;
import _Drive.example._Drive.Dto.UserDto;
import _Drive.example._Drive.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    UserDto signup(@RequestBody SignupDto signupDto){
        return authService.signup(signupDto);
    }
}
