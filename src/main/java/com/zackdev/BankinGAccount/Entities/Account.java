package com.zackdev.BankinGAccount.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;



@Data
@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Account extends AbstractEntity{
    private String iban;
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
}
