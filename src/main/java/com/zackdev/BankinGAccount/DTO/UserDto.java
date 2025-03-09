package com.zackdev.BankinGAccount.DTO;


import com.zackdev.BankinGAccount.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@Builder@AllArgsConstructor
public class UserDto  {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public static UserDto fromUserEntity(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

    }
    public static User toUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
}
