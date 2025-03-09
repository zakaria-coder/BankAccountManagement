package com.zackdev.BankinGAccount.DTO;

import com.zackdev.BankinGAccount.Entities.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@Builder@AllArgsConstructor
public class AccountDto {
    private Integer id;
    private String iban;
    private UserDto user;

    public static AccountDto fromAccountEntity (Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .iban(account.getIban())
                .user(UserDto.fromUserEntity(account.getUser()))
                .build();
    }
    public static Account toAccountEntity(AccountDto accountDto) {
        return Account
                .builder()
                .id(accountDto.getId())
                .iban(accountDto.getIban())
                .user(UserDto.toUser(accountDto.getUser()))
                .build();
    }

}
