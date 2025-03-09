package com.zackdev.BankinGAccount.Repositories;

import com.zackdev.BankinGAccount.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByFirstName(String firstName);

}
