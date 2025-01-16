package _Drive.example._Drive.Service;

import _Drive.example._Drive.Entities.Enums.TransactionMethod;
import _Drive.example._Drive.Entities.Ride;
import _Drive.example._Drive.Entities.User;
import _Drive.example._Drive.Entities.Wallet;

public interface WalletService {
    Wallet addMoneyToWallet(User user , Double amount ,
                            String transactionId , Ride ride,
                            TransactionMethod transactionMethod);
    Wallet deductMoneyfromWallet(User user , Double amount , String transactionId,
                                 Ride ride , TransactionMethod transactionMethod);

    void WithdrawAllMyMoneyFromWallet();
    Wallet createNewWallet( User user);
    Wallet findwalletById(Long walletId);
    Wallet findByUser(User user);
}
