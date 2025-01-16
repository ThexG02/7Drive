package _Drive.example._Drive.Entities;

import _Drive.example._Drive.Entities.Enums.PaymentMethod;
import _Drive.example._Drive.Entities.Enums.PaymentStatus;
import _Drive.example._Drive.Entities.Enums.TransactionMethod;
import _Drive.example._Drive.Entities.Enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionMethod transactionMethod;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private TransactionType transactionType;
    @ManyToOne
    private Wallet wallet;
    @OneToOne
    private  Ride ride;

    private String transactionid;


    @CreationTimestamp
    private LocalDateTime timestamp;
}
