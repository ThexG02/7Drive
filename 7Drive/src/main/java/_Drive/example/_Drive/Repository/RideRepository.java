package _Drive.example._Drive.Repository;

import _Drive.example._Drive.Entities.Driver;
import _Drive.example._Drive.Entities.Ride;
import _Drive.example._Drive.Entities.Rider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Long> {

    Page<Ride> findByRider(Rider rider, PageRequest pageRequest);

    Page<Ride> findByDriver(Driver driver, PageRequest pageRequest);
}
