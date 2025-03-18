package com.zackdev.BankinGAccount.service;

import com.zackdev.BankinGAccount.DTO.ContactDto;

import java.util.List;

public interface ContactService extends AbstractService<ContactDto> {
    List<ContactDto> findAllByUserId(Integer userId);
}
