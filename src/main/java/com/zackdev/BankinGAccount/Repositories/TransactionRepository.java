package com.zackdev.BankinGAccount.Repositories;

import com.zackdev.BankinGAccount.Entities.Transaction;
import com.zackdev.BankinGAccount.Enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByUserId(Integer userId);


    @Query("select sum (t.transaction_amount) from Transaction t where t.user.id = :userId")
    BigDecimal findAccountBalance(@Param("userId")Integer userId);


    @Query("select max(abs(t.transaction_amount)) from Transaction t where t.user.id = :userId and t.transaction_type= :transactionType")
    BigDecimal findHighestAmountByTransactionType(@Param("userId") Integer userId,@Param("transactionType") TransactionType transactionType);


    @Query("select t.createdAt,sum(t.transaction_amount) from Transaction t where t.user.id = :userId and t.createdAt between :start and :end ")
    Map<LocalDate, BigDecimal> findSumTransactionByDate(LocalDateTime startDate, LocalDateTime endDate,@Param("userId") Integer userId);
}
