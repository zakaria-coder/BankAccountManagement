package com.zackdev.BankinGAccount.service.impl;

import com.zackdev.BankinGAccount.DTO.AccountDto;
import com.zackdev.BankinGAccount.Entities.Account;
import com.zackdev.BankinGAccount.Exception.OperationNonPermittedException;
import com.zackdev.BankinGAccount.Repositories.AccountRepository;
import com.zackdev.BankinGAccount.Validator.ObjectsValidator;
import com.zackdev.BankinGAccount.service.AbstractService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AbstractService<AccountDto> {

    private final AccountRepository accountRepository;
    private final ObjectsValidator<AccountDto> validator;

    @Override
    public Integer save(AccountDto dto) {
        if(dto.getId() != null){
            throw new OperationNonPermittedException(
                    "Account can't be updated",
                    "save account",
                    "Account",
                    "Update not permitted"
            );
        }
        validator.validate(dto);
        Account account = AccountDto.toAccountEntity(dto);

        boolean userHasAlreadyAccount = accountRepository.findByUserId(account.getUser().getId()).isPresent();

        if(userHasAlreadyAccount){
            throw new OperationNonPermittedException(
                    "User has already an active account"
                    ,"Create Account"
                    ," Account Service"
                    ,"Account creation"
            );
        }
        //To generate random Iban before save the account
        if(account.getId() == null){
            account.setIban(generateRandomIban());
        }
        return accountRepository.save(account).getId();
    }
    @Override
    public List<AccountDto> findAll() {
        return accountRepository
                .findAll()
                .stream()
                .map(AccountDto::fromAccountEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {
        return accountRepository.findById(id).map(AccountDto::fromAccountEntity)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with ID: " + id));
    }



    @Override
    public void delete(Integer id) {
       accountRepository.deleteById(id);
    }


    private String generateRandomIban(){
        //generate firs iban then checkin

        String iban = Iban.random(CountryCode.MA).toFormattedString();
        //check if the iban is valid
        boolean ibanExist = accountRepository.findByIban(iban).isPresent();
        //check the iban if exist
          if(ibanExist){
              generateRandomIban();
          }
        // check the iban if not exist
        return iban;
    }
}
