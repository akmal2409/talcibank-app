package tech.talci.talcibankapp.converters;

import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import tech.talci.talcibankapp.commands.CardCommand;
import tech.talci.talcibankapp.domain.Card;

public class CardCommandToCard implements Converter<CardCommand, Card> {

    @Nullable
    @Synchronized
    @Override
    public Card convert(CardCommand cardCommand) {
        return null;
    }
}
