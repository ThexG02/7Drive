package _Drive.example._Drive.Repository;

import _Drive.example._Drive.Entities.Payment;
import _Drive.example._Drive.Entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository  extends JpaRepository<Payment ,Long> {
Optional<Payment> findByRide(Ride ride);
}
