package _Drive.example._Drive.Service;

import _Drive.example._Drive.Entities.Enums.PaymentStatus;
import _Drive.example._Drive.Entities.Payment;
import _Drive.example._Drive.Entities.Ride;

public interface PaymentService {
    void processPayment(Ride ride);
    Payment createNewPayment(Ride ride);
    void updatePayment(Payment payment , PaymentStatus status);
}
