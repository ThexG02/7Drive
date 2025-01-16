package _Drive.example._Drive.Service.impl;

import _Drive.example._Drive.Service.DistanceService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import org.locationtech.jts.geom.Point;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DistanceserviceOSRMimpl implements DistanceService {

   static private final String OSRM_API_BASE_URL="http://router.project-osrm.org/route/v1/driving/";
    @Override
    public Double calcDistance(Point src, Point dist) {
       try{
           String uri = src.getX()+","+src.getY()+";"+dist.getX()+","+dist.getY();
           OSRMResponseDto responseDto= RestClient.builder()
                   .baseUrl(OSRM_API_BASE_URL)
                   .build()
                   .get()
                   .uri(uri)
                   .retrieve()
                   .body(OSRMResponseDto.class);
           return  responseDto.getRoutes().get(0).getDistance()/1000.0;
       } catch (Exception e){
           throw new RuntimeException("Error getting data from OSRM "+ e.getMessage());
       }

    }
}
@Data
class OSRMResponseDto{
    private List<OSRMRoute> routes;
}
@Data
class OSRMRoute{
    private Double distance;
}