package com.zackdev.BankinGAccount.Repositories;

import com.zackdev.BankinGAccount.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Integer> {
}
