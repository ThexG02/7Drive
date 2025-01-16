package _Drive.example._Drive.Service.impl;

import _Drive.example._Drive.Entities.Enums.TransactionMethod;
import _Drive.example._Drive.Entities.Enums.TransactionType;
import _Drive.example._Drive.Entities.Ride;
import _Drive.example._Drive.Entities.User;
import _Drive.example._Drive.Entities.Wallet;
import _Drive.example._Drive.Entities.WalletTransaction;
import _Drive.example._Drive.Exceptions.ResourceNotFoundException;
import _Drive.example._Drive.Repository.WalletRepository;
import _Drive.example._Drive.Service.WalletService;
import _Drive.example._Drive.Service.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl  implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletTransactionService walletTransactionService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
       Wallet wallet = findByUser(user);
       wallet.setBalance(wallet.getBalance()+amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionid(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.Credit)
                .transactionMethod(transactionMethod)
                .amount(amount)
                .build();
        walletTransactionService.createNewWalletTransaction(walletTransaction);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet deductMoneyfromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {

        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance()-amount);
        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionid(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.Debit)
                .transactionMethod(transactionMethod)
                .amount(amount)
                .build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);

        return walletRepository.save(wallet);
    }

    @Override
    public void WithdrawAllMyMoneyFromWallet() {

    }

    @Override
    public Wallet createNewWallet(User user) {
       Wallet wallet = new Wallet();
       wallet.setUser(user);
       return  walletRepository.save(wallet);
    }

    @Override
    public Wallet findwalletById(Long  walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(()-> new ResourceNotFoundException("wallet not found with id : " +walletId));

    }

    @Override
    public Wallet findByUser(User user) {
       return walletRepository.findByUser(user)
               .orElseThrow(()-> new ResourceNotFoundException("wallet not for the User with userId :"+user.getId()));
    }
}
