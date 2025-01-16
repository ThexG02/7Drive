package _Drive.example._Drive.Repository;

import _Drive.example._Drive.Entities.User;
import _Drive.example._Drive.Entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long> {
 Optional<Wallet> findByUser(User user);
}
