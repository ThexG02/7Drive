package _Drive.example._Drive.Service;

import _Drive.example._Drive.Entities.Enums.PaymentStatus;
import _Drive.example._Drive.Entities.Payment;
import _Drive.example._Drive.Entities.Ride;
import _Drive.example._Drive.Entities.WalletTransaction;

public interface WalletTransactionService {


    void createNewWalletTransaction(WalletTransaction walletTransaction);
}
