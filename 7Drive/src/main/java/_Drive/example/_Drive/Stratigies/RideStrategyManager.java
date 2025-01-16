package _Drive.example._Drive.Stratigies;

import _Drive.example._Drive.Stratigies.impl.CalculateFairStrategy2Default;
import _Drive.example._Drive.Stratigies.impl.CaluculateFair1Surge;
import _Drive.example._Drive.Stratigies.impl.FindDriverStrategy1HighestRated;
import _Drive.example._Drive.Stratigies.impl.FindDriverStrategy2Nearest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {
    private final FindDriverStrategy2Nearest findDriverStrategy2Nearest;
    private final FindDriverStrategy1HighestRated findDriverStrategy1HighestRated;
    private final CaluculateFair1Surge caluculateFair1Surge;
    private final CalculateFairStrategy2Default calculateFairStrategy2Default;


    //Ride Strategy Manager will decide DriverMatching Strategy
    public FindDriver MatchDriver(double riderRating){
        if(riderRating>=4.8){
            return findDriverStrategy1HighestRated;
        }
        else{
            return findDriverStrategy2Nearest;
        }

    }


    //Ride Strategy Manager will decide Fare calculation strategy
    public CalculateFare calculateFare(){

        //Surge timings (high demand low supply) : 6 p.m. -9:30 p.m.
        LocalTime surgeStart = LocalTime.of(18,0);
        LocalTime surgeEnd = LocalTime.of(21,30);
        LocalTime currentTime = LocalTime.now();
        boolean isSurgeTime = currentTime.isAfter(surgeStart)&&currentTime.isBefore(surgeEnd);

        if(isSurgeTime){
            return caluculateFair1Surge;
        }
        else {
            return calculateFairStrategy2Default;
        }

    }
}
