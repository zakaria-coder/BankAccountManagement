package com.zackdev.BankinGAccount.service;


import com.zackdev.BankinGAccount.DTO.UserDto;

public interface UserService extends AbstractService<UserDto>{
    Integer validateAccount (Integer id);
    Integer InvalidateAccount (Integer id);

}
