package _Drive.example._Drive.Stratigies;

import _Drive.example._Drive.Entities.Enums.PaymentMethod;
import _Drive.example._Drive.Stratigies.impl.CashPaymentStaryegy;
import _Drive.example._Drive.Stratigies.impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CashPaymentStaryegy cashPaymentStaryegy;

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod){
        return switch (paymentMethod){
            case wallet -> walletPaymentStrategy;
            case card -> null;
            case cash ->  cashPaymentStaryegy;
            case UPI -> null;
        };
    }

}
