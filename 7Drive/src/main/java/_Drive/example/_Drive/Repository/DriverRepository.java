package _Drive.example._Drive.Repository;

import _Drive.example._Drive.Entities.Driver;
import _Drive.example._Drive.Entities.User;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

//ST_Distance(point1 , point2)                    : geospatial method (postgis)
//ST_DWithin(point1 , range(eg. 10, 100, 10000))  : geospatial method (postgis)
@Repository
public interface DriverRepository extends JpaRepository<Driver,Long > {

    //Query to provide the list of 10 nearest drivers from the database based on current location provided from the frontend
    @Query(value = "SELECT d.* , ST_Distance(d.current_location, :pickupLocation) AS distance " +
                    "FROM driver  d "+
                    "WHERE d.available = true AND ST_DWithin(d.current_location , :pickupLocation , 10000) " +
                    "ORDER BY distance "+
                    "LIMIT 10 ", nativeQuery = true
    )
    List<Driver> findTenNearestDrivers(Point pickupLocation);


    //Query tp provide the List of 10 top-rated drivers from the databse based on the current location provided from the front end
    @Query(value = "SELECT d.* "+
                    "FROM driver d "+
                    "WHERE d.available = true AND ST_DWithin(d.current_location , :pickupLocation , 15000) "+
                    "ORDER BY d.rating DESC "+
                    "LIMIT 10 " , nativeQuery = true)
    List<Driver> findTenTopRatedNearbyDrivers(Point pickupLocation);

    Driver findByUser(User user);
}
