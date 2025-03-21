package _Drive.example._Drive.Repository;

import _Drive.example._Drive.Entities.Rider;
import _Drive.example._Drive.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RiderRepository extends JpaRepository<Rider, Long> {
    Optional<Rider> findByUser(User user);}
