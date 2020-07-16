package tech.talci.talcibankapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.talci.talcibankapp.domain.Currency;

@Getter
@Setter
@NoArgsConstructor
public class TransactionCommand {

    private Long id;
    private AccountCommand sender;
    private Long recipient;
    private String description;
    private double amount;
    private Currency currency;
}
