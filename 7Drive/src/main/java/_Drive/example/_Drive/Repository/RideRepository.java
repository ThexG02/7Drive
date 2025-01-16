package _Drive.example._Drive.Repository;

import _Drive.example._Drive.Entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Long> {
}
