package _Drive.example._Drive.Entities;

import _Drive.example._Drive.Entities.Enums.PaymentMethod;
import _Drive.example._Drive.Entities.Enums.PaymentStatus;
import com.sun.jdi.event.StepEvent;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne
    private Wallet wallet;
    @OneToOne
    private  Ride ride;

    private String transactionid;


    @CreationTimestamp
    private LocalDateTime timestamp;
}
