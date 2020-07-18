package tech.talci.talcibankapp.converters;


import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import tech.talci.talcibankapp.commands.CardCommand;
import tech.talci.talcibankapp.domain.Card;

public class CardToCardCommand implements Converter<Card, CardCommand> {

    @Nullable
    @Synchronized
    @Override
    public CardCommand convert(Card source) {

        if(source == null){
            return null;
        }

        CardCommand command = new CardCommand();
        command.setHoldersName(source.getHoldersName());
        command.setCurrency(source.getCurrency());
        command.setCardType(source.getCardType());
        command.setId(source.getId());
        if(source.getClient().getId() != null){
            command.setClientID(source.getClient().getId());
        }
        return command;
    }
}
