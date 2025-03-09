package com.zackdev.BankinGAccount.DTO;
import com.zackdev.BankinGAccount.Entities.Contact;
import com.zackdev.BankinGAccount.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ContactDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String iban;
    private Integer userid;

    public static ContactDto fromContactEntity(Contact contact) {
        return ContactDto.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .userid(contact.getUser().getId())
                .build();
    }

    public static Contact toContactDto(ContactDto contactDto) {
        return Contact.builder()
                .id(contactDto.getId())
                .firstName(contactDto.getFirstName())
                .lastName(contactDto.getLastName())
                .email(contactDto.getEmail())
                .iban(contactDto.getIban())
                .user(User.builder()
                        .id(contactDto.getUserid())
                        .build())
        .build() ;
    }
}
