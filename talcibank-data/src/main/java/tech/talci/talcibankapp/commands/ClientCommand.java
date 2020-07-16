package tech.talci.talcibankapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ClientCommand {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String secretPhrase;
    private String address;
    private String phoneNumber;
    private LocalDate birthDate;
}
