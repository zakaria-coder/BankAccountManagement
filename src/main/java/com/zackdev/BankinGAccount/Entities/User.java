package com.zackdev.BankinGAccount.Entities;


import com.zackdev.BankinGAccount.Enums.Sexe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_profile")
public class User extends AbstractEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Sexe sexe;
    private String phoneNumber;
    private boolean active;
    @OneToOne
    private Role role;
    @OneToOne
    private Address address;
    @OneToOne
    private Account account;

   @OneToMany(mappedBy = "user")
   private List<Transaction> transactionList;
   @OneToMany(mappedBy = "user")
   private List<Contact> contactList;


}
