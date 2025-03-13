package com.zackdev.BankinGAccount.Repositories;

import com.zackdev.BankinGAccount.Entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
