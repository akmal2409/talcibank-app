package tech.talci.talcibankapp.converters;

import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import tech.talci.talcibankapp.commands.CardCommand;
import tech.talci.talcibankapp.domain.Card;
import tech.talci.talcibankapp.domain.Client;
import tech.talci.talcibankapp.services.ClientService;

public class CardCommandToCard implements Converter<CardCommand, Card> {

    ClientService clientService;

    public CardCommandToCard(ClientService clientService) {
        this.clientService = clientService;
    }

    @Nullable
    @Synchronized
    @Override
    public Card convert(CardCommand source) {

        if(source == null){
            return null;
        }

        Card card = new Card();
        card.setCurrency(source.getCurrency());
        card.setCardType(source.getCardType());
        card.setHoldersName(source.getHoldersName());
        card.setId(source.getId());
        card.setClient(clientService.findById(source.getClientID()));
        return card;
    }
}
