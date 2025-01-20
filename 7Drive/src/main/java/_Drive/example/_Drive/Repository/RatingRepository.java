package _Drive.example._Drive.Repository;

import _Drive.example._Drive.Entities.Driver;
import _Drive.example._Drive.Entities.Rating;
import _Drive.example._Drive.Entities.Ride;
import _Drive.example._Drive.Entities.Rider;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByRider(Rider rider);
    List<Rating> findByDriver(Driver driver);

    Optional<Rating> findByRide(Ride ride);
}
