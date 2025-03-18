package com.zackdev.BankinGAccount.service;

import com.zackdev.BankinGAccount.DTO.TransactionDto;
import com.zackdev.BankinGAccount.Entities.Transaction;

import java.util.List;


public interface TransactionService extends AbstractService<TransactionDto> {
    List<TransactionDto> findAllByUserid(Integer userId);
}
