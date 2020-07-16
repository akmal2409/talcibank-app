package tech.talci.talcibankapp.converters;

import org.junit.Before;
import org.junit.Test;
import tech.talci.talcibankapp.commands.CardCommand;
import tech.talci.talcibankapp.domain.Card;
import tech.talci.talcibankapp.domain.CardType;
import tech.talci.talcibankapp.domain.Currency;

import javax.swing.plaf.ColorUIResource;

import static org.junit.Assert.*;

public class CardCommandToCardTest {

    public static final Long ID_VALUE = Long.valueOf(2L);
    public static final String HOLDERS_NAME = "Test Name";
    public static final CardType CARD_TYPE = CardType.VISA_PLATINUM;
    public static final Currency CURRENCY = Currency.DOLLAR;

    CardCommandToCard converter;

    @Before
    public void setUp() throws Exception {
        converter = new CardCommandToCard();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new CardCommand()));
    }

    @Test
    public void convert() {
        //given
        CardCommand command = new CardCommand();
        command.setId(ID_VALUE);
        command.setCardType(CARD_TYPE);
        command.setCurrency(CURRENCY);
        command.setHoldersName(HOLDERS_NAME);

        //when
        Card card = converter.convert(command);

        //then
        assertNotNull(card);
        assertEquals(ID_VALUE, card.getId());
        assertEquals(HOLDERS_NAME, card.getHoldersName());
        assertEquals(CURRENCY, card.getCurrency());
        assertEquals(CARD_TYPE, card.getCardType());
    }
}