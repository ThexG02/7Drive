package _Drive.example._Drive.Repository;

import _Drive.example._Drive.Entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiderRepository extends JpaRepository<Rider, Long> {
}
