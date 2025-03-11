package com.zackdev.BankinGAccount.service.impl;

import com.zackdev.BankinGAccount.DTO.UserDto;
import com.zackdev.BankinGAccount.Entities.User;
import com.zackdev.BankinGAccount.Repositories.UserRepository;
import com.zackdev.BankinGAccount.Validator.ObjectsValidator;
import com.zackdev.BankinGAccount.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
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
}
