package _Drive.example._Drive.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupDto {
    private long id;
    private String EMail;
    private String password;
    private String name;
}
