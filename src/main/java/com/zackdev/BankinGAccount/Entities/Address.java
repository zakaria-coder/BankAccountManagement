package com.zackdev.BankinGAccount.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Address extends AbstractEntity {
    private String street ;
    private String houseNumber ;
    private Integer zipCode ;
    private String city ;
    private String country ;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
