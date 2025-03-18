package com.zackdev.BankinGAccount.service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public interface StatisticService {
    Map<LocalDate, BigDecimal> findSumTransactionByDate(LocalDate startDate, LocalDate endDate,Integer userId);
    BigDecimal getAccountBalance(Integer userId);
    BigDecimal getHighestAmountSend(Integer userId);
    BigDecimal getHighestAmountReceive(Integer userId);
}
