package com.zackdev.BankinGAccount.Entities;

import com.zackdev.BankinGAccount.Enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;


@Data
@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Transaction  extends  AbstractEntity{
    private BigDecimal transaction_amount;
    @Enumerated(EnumType.STRING)
    private TransactionType transaction_type;
    private String receiver_iban;
    private String sender_iban;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
