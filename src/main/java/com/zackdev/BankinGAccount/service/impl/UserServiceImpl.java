package com.zackdev.BankinGAccount.service.impl;

import com.zackdev.BankinGAccount.DTO.AccountDto;
import com.zackdev.BankinGAccount.DTO.UserDto;
import com.zackdev.BankinGAccount.Entities.User;
import com.zackdev.BankinGAccount.Repositories.UserRepository;
import com.zackdev.BankinGAccount.Validator.ObjectsValidator;
import com.zackdev.BankinGAccount.service.AccountService;
import com.zackdev.BankinGAccount.service.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    protected AccountService accountService;
    private final ObjectsValidator<UserDto> validator;

    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toUser(dto);
        return userRepository.save(user).getId();
    }


    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::fromUserEntity)
                .collect(Collectors
                        .toList());
    }

    @Override
    public UserDto findById(Integer id) {
        return userRepository.findById(id).map(UserDto::fromUserEntity)
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found with id: " + id));
    }


    @Override
    public void delete(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public Integer validateAccount(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("User not found with id: " + id) );

        user.setActive(true);
        //create the account with user
        AccountDto account = AccountDto
                .builder()
                .user(UserDto.fromUserEntity(user))
                .build();
        accountService.save(account);
        userRepository.save(user);
        return account.getId();
    }

    @Override
    public Integer InvalidateAccount(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("User not found with id: " + id) );

        if (!user.isActive()) {
            throw new EntityExistsException("User is already inactive");
        }
        user.setActive(false);
        return userRepository.save(user).getId();
    }
}
