package _Drive.example._Drive.Service.impl;

import _Drive.example._Drive.Entities.Enums.PaymentStatus;
import _Drive.example._Drive.Entities.Payment;
import _Drive.example._Drive.Entities.Ride;
import _Drive.example._Drive.Exceptions.ResourceNotFoundException;
import _Drive.example._Drive.Repository.PaymentRepository;
import _Drive.example._Drive.Service.PaymentService;
import _Drive.example._Drive.Stratigies.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceimpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;

    @Override
    public void processPayment(Ride ride) {
        Payment payment = paymentRepository.findByRide(ride)
                .orElseThrow(()-> new ResourceNotFoundException("Payment not found for ride with id :"+ride.getId()));
        paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);
    }

    @Override
    public Payment createNewPayment(Ride ride) {
        Payment payment = Payment.builder()
                .ride(ride)
                .paymentMethod(ride.getPaymentMethod())
                .paymentStatus(PaymentStatus.pending)
                .amount(ride.getFare())
                .build();
        return paymentRepository.save(payment);
    }

    @Override
    public void updatePayment(Payment payment, PaymentStatus status) {
        payment.setPaymentStatus(status);
        paymentRepository.save(payment);
    }
}
