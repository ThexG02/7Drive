package _Drive.example._Drive.Service.impl;

import _Drive.example._Drive.Entities.Payment;
import _Drive.example._Drive.Entities.Ride;
import _Drive.example._Drive.Entities.WalletTransaction;
import _Drive.example._Drive.Repository.WalletTransactionRepository;
import _Drive.example._Drive.Service.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {

        walletTransactionRepository.save(walletTransaction);
    }
}
