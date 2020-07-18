package tech.talci.talcibankapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.talci.talcibankapp.domain.CardType;
import tech.talci.talcibankapp.domain.Currency;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CardCommand {

    private Long id;

    @NotBlank(message = "Holders Name cannot be blank")
    private String holdersName;
    private CardType cardType;
    private Currency currency;

    @NotBlank(message = "Client ID cannot be blank")
    private Long clientID;
}
