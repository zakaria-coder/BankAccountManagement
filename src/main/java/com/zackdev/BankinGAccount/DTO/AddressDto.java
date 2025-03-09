package com.zackdev.BankinGAccount.DTO;

import com.zackdev.BankinGAccount.Entities.Address;
import com.zackdev.BankinGAccount.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AddressDto {
    private Integer id;
    private String street ;
    private String houseNumber ;
    private Integer zipCode ;
    private String city ;
    private String country ;
    private Integer userid;

    public static AddressDto fromAddressEntity(Address address) {

        return AddressDto.builder()
                .id(address.getId())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .country(address.getCountry())
                .userid(address.getUser().getId())
                .build();
    }

    public static Address toAddressEntity(AddressDto addressDto) {
        return Address.builder()
                .id(addressDto.getId())
                .street(addressDto.getStreet())
                .houseNumber(addressDto.getHouseNumber())
                .zipCode(addressDto.getZipCode())
                .city(addressDto.getCity())
                .country(addressDto.getCountry())
                .user(
                        User.builder()
                        .id(addressDto.getUserid())
                        .build()
                )
                .build();
    }



}
