package com.zackdev.BankinGAccount.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zackdev.BankinGAccount.DTO.ContactDto;
import com.zackdev.BankinGAccount.Entities.Contact;
import com.zackdev.BankinGAccount.Repositories.ContactRepository;
import com.zackdev.BankinGAccount.Validator.ObjectsValidator;
import com.zackdev.BankinGAccount.service.ContactService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final ObjectsValidator<ContactDto> validator;

    @Override
    public List<ContactDto> findAll() {
        return contactRepository
                .findAll()
                .stream()
                .map(ContactDto::fromContactEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ContactDto findById(Integer id) {
        return contactRepository.findById(id)
                .map(ContactDto::fromContactEntity)
                .orElseThrow(() -> new EntityNotFoundException("Contact not found"));
    }

    @Override
    public Integer save(ContactDto dto) {
        validator.validate(dto);
        Contact contact = ContactDto.toContactEntity(dto);
        return contactRepository.save(contact).getId();
    }

    @Override
    public void delete(Integer t) {

        contactRepository.deleteById(t);
    }
}
