package _Drive.example._Drive.Service.impl;

import _Drive.example._Drive.Dto.DriverDto;
import _Drive.example._Drive.Dto.SignupDto;
import _Drive.example._Drive.Dto.UserDto;
import _Drive.example._Drive.Entities.Driver;
import _Drive.example._Drive.Entities.Enums.Roles;
import _Drive.example._Drive.Entities.User;
import _Drive.example._Drive.Exceptions.ResourceNotFoundException;
import _Drive.example._Drive.Exceptions.RuntimeConflictException;
import _Drive.example._Drive.Repository.UserRepository;
import _Drive.example._Drive.Service.AuthService;
import _Drive.example._Drive.Service.DriverService;
import _Drive.example._Drive.Service.RiderService;
import _Drive.example._Drive.Service.WalletService;
import _Drive.example._Drive.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceimpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;



    @Override
    public String[] login(String email, String password) {
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email,password)
        );

        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken= jwtService.generateRefreshToken(user);

        return new String[]{accessToken,refreshToken};
    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto) {
       User user= userRepository.findByEmail(signupDto.getEMail()).orElse(null);
        if(user != null){
            throw new RuntimeConflictException("Can not signup , user alredy extist with email"+ signupDto.getEMail());
        }
        User mappedUser = modelMapper.map(signupDto,User.class);
        mappedUser.setRoles(Set.of(Roles.RIDER));
        User saveduser= userRepository.save(mappedUser);

        riderService.createNewRider(saveduser);
        walletService.createNewWallet(saveduser);
        return modelMapper.map(saveduser,UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId, String vehicleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id "+userId));

        if(user.getRoles().contains(Roles.DRIVER))
            throw new RuntimeConflictException("User with id "+userId+" is already a Driver");


        Driver createDriver = Driver.builder()
                .user(user)
                .rating(0.0)
                .vehicalId(vehicleId)
                .available(true)
                .build();
        user.getRoles().add(Roles.DRIVER);
        userRepository.save(user);
        Driver savedDriver = driverService.createNewDriver(createDriver);
        return modelMapper.map(savedDriver, DriverDto.class);
    }

    @Override
    public String refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found " +
                "with id: "+userId));

        return jwtService.generateAccessToken(user);
    }


}
