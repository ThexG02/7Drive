package _Drive.example._Drive.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DriverDto {
    private  Double Rating;
    private UserDto user;
}
