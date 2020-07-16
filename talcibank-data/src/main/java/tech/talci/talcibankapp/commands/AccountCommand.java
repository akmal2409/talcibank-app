package tech.talci.talcibankapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.talci.talcibankapp.domain.AccountType;
import tech.talci.talcibankapp.domain.Client;

@Getter
@Setter
@NoArgsConstructor
public class AccountCommand {

    private Long id;
    private String name;
    private String clientsName;
    private AccountType accountType;
    private Long clientID;
}
