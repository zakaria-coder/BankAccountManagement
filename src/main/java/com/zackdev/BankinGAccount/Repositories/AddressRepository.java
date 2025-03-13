package com.zackdev.BankinGAccount.Repositories;

import com.zackdev.BankinGAccount.Entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
