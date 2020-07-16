package tech.talci.talcibankapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.talci.talcibankapp.domain.CardType;
import tech.talci.talcibankapp.domain.Currency;

@Getter
@Setter
@NoArgsConstructor
public class CardCommand {

    private Long id;
    private String holdersName;
    private CardType cardType;
    private Currency currency;
    private Long clientID;
}
