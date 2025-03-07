package com.zackdev.BankinGAccount.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Role extends AbstractEntity {
    private String roleName;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
