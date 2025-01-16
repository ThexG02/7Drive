package _Drive.example._Drive.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private Double Balance;

    @OneToMany(mappedBy="wallet",fetch = FetchType.LAZY)
    private List<WalletTransaction> transactions;

}
