package com.zackdev.BankinGAccount.service.impl;

import com.zackdev.BankinGAccount.DTO.AddressDto;
import com.zackdev.BankinGAccount.Entities.Address;
import com.zackdev.BankinGAccount.Repositories.AddressRepository;
import com.zackdev.BankinGAccount.Validator.ObjectsValidator;
import com.zackdev.BankinGAccount.service.AddressService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final ObjectsValidator<AddressDto> validator;

    @Override
    public List<AddressDto> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(AddressDto::fromAddressEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer id) {
        return addressRepository.findById(id)
                .map(AddressDto::fromAddressEntity)
                .orElseThrow(() -> new EntityNotFoundException("No such address"));
    }

    @Override
    public Integer save(AddressDto dto) {
        validator.validate(dto);
        Address address = AddressDto.toAddressEntity(dto);
        return  addressRepository.save(address).getId();
    }

    @Override
    public void delete(Integer t) {
        if(addressRepository.existsById(t)){
            addressRepository.deleteById(t);
        }
        else throw new EntityNotFoundException("No such address");
    }
}
