package _Drive.example._Drive.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor
@AllArgsConstructor
public class RiderDto {
    private UserDto user;
    private  double Rating;
}
