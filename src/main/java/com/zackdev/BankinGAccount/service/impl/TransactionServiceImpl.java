package com.zackdev.BankinGAccount.service.impl;

import com.zackdev.BankinGAccount.DTO.TransactionDto;
import com.zackdev.BankinGAccount.Entities.Transaction;
import com.zackdev.BankinGAccount.Enums.TransactionType;
import com.zackdev.BankinGAccount.Repositories.TransactionRepository;
import com.zackdev.BankinGAccount.Validator.ObjectsValidator;
import com.zackdev.BankinGAccount.service.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final ObjectsValidator<TransactionDto> validator;
    private final TransactionRepository transactionRepository;

    @Override
    public List<TransactionDto> findAll() {
        List<Transaction> transactions = transactionRepository.findAll();
        if (transactionRepository.findAll().isEmpty()) {
            throw  new EntityNotFoundException("No Transaction Found");
        }
        return transactions
                .stream()
                .map(TransactionDto::fromTransactionEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto findById(Integer id) {
        return transactionRepository.findById(id).map(TransactionDto::fromTransactionEntity).orElseThrow(() -> new EntityNotFoundException("No Transaction Found by this Id:" + id));
    }

    @Override
    public Integer save(TransactionDto dto) {
        validator.validate(dto);
        Transaction transaction = TransactionDto.toTransactionEntity(dto);
        BigDecimal amount= transaction
                .getTransaction_amount()
                .multiply(BigDecimal
                        .valueOf(transactionType(transaction.getTransaction_type())));
        transaction.setTransaction_amount(amount);
        return transactionRepository.save(transaction).getId();
    }

    @Override
    public void delete(Integer t) {
         transactionRepository.deleteById(t);

    }


    private int transactionType(TransactionType transactionType) {
        return TransactionType.Send == transactionType ? 1 : -1;
    }

    @Override
    public List<TransactionDto> findAllByUserid(Integer userId) {
        return transactionRepository.findAllByUserId(userId)
                .stream()
                .map(TransactionDto::fromTransactionEntity)
                .collect(Collectors.toList());
    }
}
