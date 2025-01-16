package _Drive.example._Drive.Stratigies.impl;

import _Drive.example._Drive.Entities.Driver;
import _Drive.example._Drive.Entities.Enums.TransactionMethod;
import _Drive.example._Drive.Entities.Payment;
import _Drive.example._Drive.Repository.PaymentRepository;
import _Drive.example._Drive.Service.WalletService;
import _Drive.example._Drive.Stratigies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashPaymentStaryegy implements PaymentStrategy {
    private final WalletService walletService;
    private final PaymentRepository paymentRepository;


    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        double platformCommission = payment.getAmount()*PLATFORM_COMMISSION;
        walletService.deductMoneyfromWallet(driver.getUser(),platformCommission,
                payment.getRide(), TransactionMethod.Ride);
    }
}
