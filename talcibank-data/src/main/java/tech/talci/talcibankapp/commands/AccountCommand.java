package tech.talci.talcibankapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.talci.talcibankapp.domain.AccountType;
import tech.talci.talcibankapp.domain.Client;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AccountCommand {

    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;
    private AccountType accountType;

    @NotBlank(message = "Client ID cannot be blank")
    private Long clientID;
}
