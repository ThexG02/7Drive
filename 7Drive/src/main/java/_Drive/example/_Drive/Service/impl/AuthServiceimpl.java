package _Drive.example._Drive.Service.impl;

import _Drive.example._Drive.Dto.DriverDto;
import _Drive.example._Drive.Dto.SignupDto;
import _Drive.example._Drive.Dto.UserDto;
import _Drive.example._Drive.Entities.Enums.Roles;
import _Drive.example._Drive.Entities.User;
import _Drive.example._Drive.Exceptions.RuntimeConflictException;
import _Drive.example._Drive.Repository.UserRepository;
import _Drive.example._Drive.Service.AuthService;
import _Drive.example._Drive.Service.RiderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceimpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;

    @Override
    public String login(String email, String password) {

        return "";
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
//  creating user related entities
        riderService.createNewRider(saveduser);
        // Todo add wallet related service here

        return modelMapper.map(saveduser,UserDto.class);
    }

    @Override
    public DriverDto OnBoardDriver(Long userid) {
        return null;
    }
}
