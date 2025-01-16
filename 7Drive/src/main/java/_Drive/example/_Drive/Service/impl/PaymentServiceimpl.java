package _Drive.example._Drive.Service.impl;

import _Drive.example._Drive.Entities.Enums.PaymentStatus;
import _Drive.example._Drive.Entities.Payment;
import _Drive.example._Drive.Entities.Ride;
import _Drive.example._Drive.Repository.PaymentRepository;
import _Drive.example._Drive.Service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceimpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Ride ride) {

    }

    @Override
    public Payment createNewPayment(Ride ride) {
        return null;
    }

    @Override
    public void updatePayment(Payment payment, PaymentStatus status) {

    }
}
