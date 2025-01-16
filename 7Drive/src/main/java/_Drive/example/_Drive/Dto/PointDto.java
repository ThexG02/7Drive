package _Drive.example._Drive.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PointDto {
    private double[]coordinates;
    private String Type="Point";
    public PointDto(double[] coordinates){this.coordinates=coordinates;}
}
