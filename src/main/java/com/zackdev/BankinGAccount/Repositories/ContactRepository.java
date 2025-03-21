package com.zackdev.BankinGAccount.Repositories;

import com.zackdev.BankinGAccount.Entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> finAllByUserId(Integer userId);
}
