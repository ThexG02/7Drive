package _Drive.example._Drive.Dto;

import _Drive.example._Drive.Entities.Enums.TransactionMethod;
import _Drive.example._Drive.Entities.Enums.TransactionType;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class WalletTransactionDto {

    private Long id;

    private Double amount;

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

    private RideDto ride;

    private String transactionId;

    private WalletDto wallet;

    private LocalDateTime timeStamp;

}
