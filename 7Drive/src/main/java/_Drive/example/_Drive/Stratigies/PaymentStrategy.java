package _Drive.example._Drive.Stratigies;

import _Drive.example._Drive.Entities.Payment;

public interface PaymentStrategy {
    Double PLATFORM_COMMISSION =0.3;
    void processPayment(Payment payment);
}
