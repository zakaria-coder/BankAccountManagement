package com.zackdev.BankinGAccount.service.impl;

import com.zackdev.BankinGAccount.Enums.TransactionType;
import com.zackdev.BankinGAccount.Repositories.TransactionRepository;
import com.zackdev.BankinGAccount.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;




@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final TransactionRepository transactionRepository;
    @Override
    public Map<LocalDate, BigDecimal> findSumTransactionByDate(LocalDate startDate, LocalDate endDate,Integer userId) {
        LocalDateTime start = LocalDateTime.of(startDate,LocalTime.of(0,0,0));
        LocalDateTime end = LocalDateTime.of(endDate,LocalTime.of(23,59,59));
        return transactionRepository.findSumTransactionByDate(start,end,userId);
    }

    @Override
    public BigDecimal getAccountBalance(Integer userId) {
        return transactionRepository.findAccountBalance(userId);
    }

    @Override
    public BigDecimal getHighestAmountSend(Integer userId) {
        return transactionRepository.findHighestAmountByTransactionType(userId, TransactionType.Send);
    }

    @Override
    public BigDecimal getHighestAmountReceive(Integer userId) {
        return transactionRepository.findHighestAmountByTransactionType(userId, TransactionType.Receive);
    }
}
