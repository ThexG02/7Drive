package _Drive.example._Drive.Stratigies.impl;

import _Drive.example._Drive.Entities.Driver;
import _Drive.example._Drive.Entities.Enums.PaymentStatus;
import _Drive.example._Drive.Entities.Enums.TransactionMethod;
import _Drive.example._Drive.Entities.Payment;
import _Drive.example._Drive.Entities.Rider;
import _Drive.example._Drive.Repository.PaymentRepository;
import _Drive.example._Drive.Service.WalletService;
import _Drive.example._Drive.Stratigies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {
    private final WalletService walletService;
    private final PaymentRepository paymentRepository;


    /*
    * Rider had 232 , Driver had 500
    * Ride cost is 100 , commission 30
    * Rider ->232-100 =132
    * Driver ->500+(100-30) =570
    */
    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();

        walletService.deductMoneyfromWallet(rider.getUser(),
                payment.getAmount(), null, payment.getRide(), TransactionMethod.Ride);
        double drivercut = payment.getAmount()*(1-PLATFORM_COMMISSION);

        walletService.addMoneyToWallet(driver.getUser(), drivercut,null, payment.getRide(), TransactionMethod.Ride);

        payment.setPaymentStatus(PaymentStatus.compelete);
        paymentRepository.save(payment);}
}
