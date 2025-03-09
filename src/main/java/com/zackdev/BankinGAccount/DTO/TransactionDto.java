package com.zackdev.BankinGAccount.DTO;


import com.zackdev.BankinGAccount.Entities.Transaction;
import com.zackdev.BankinGAccount.Entities.User;
import com.zackdev.BankinGAccount.Enums.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TransactionDto {

    private Integer transactionId;
    private BigDecimal transaction_amount;
    private TransactionType transaction_type;
    private String receiver_iban;
    private String sender_iban;
    private Integer userid;


    public static TransactionDto fromTransactionEntity(Transaction transaction) {
        return TransactionDto.builder()
                .transactionId(transaction.getId())
                .transaction_amount(transaction.getTransaction_amount())
                .transaction_type(transaction.getTransaction_type())
                .receiver_iban(transaction.getReceiver_iban())
                .sender_iban(transaction.getSender_iban())
                .userid(transaction.getUser().getId())
                .build();
    }

    public static Transaction toTransactionEntity(TransactionDto transactionDto) {
        return Transaction.builder()
                .id(transactionDto.getTransactionId())
                .transaction_amount(transactionDto.getTransaction_amount())
                .transaction_type(transactionDto.getTransaction_type())
                .receiver_iban(transactionDto.getReceiver_iban())
                .sender_iban(transactionDto.getSender_iban())
                .user(
                        User.builder()
                                .id(transactionDto.getUserid())
                                .build()
                )
                .build();
    }

}
