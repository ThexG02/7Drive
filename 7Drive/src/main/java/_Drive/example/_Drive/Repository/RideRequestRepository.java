package _Drive.example._Drive.Repository;

import _Drive.example._Drive.Entities.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {
}
