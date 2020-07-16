package tech.talci.talcibankapp.converters;

import org.junit.Before;
import org.junit.Test;
import tech.talci.talcibankapp.commands.CardCommand;
import tech.talci.talcibankapp.domain.Card;
import tech.talci.talcibankapp.domain.CardType;
import tech.talci.talcibankapp.domain.Currency;

import static org.junit.Assert.*;

public class CardToCardCommandTest {

    public static final Long ID_VALUE = Long.valueOf(2L);
    public static final String HOLDERS_NAME = "Test Name";
    public static final CardType CARD_TYPE = CardType.VISA_PLATINUM;
    public static final Currency CURRENCY = Currency.DOLLAR;

    CardToCardCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new CardToCardCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Card()));
    }

    @Test
    public void convert() {
        //given
        Card card = new Card();
        card.setId(ID_VALUE);
        card.setHoldersName(HOLDERS_NAME);
        card.setCardType(CARD_TYPE);
        card.setCurrency(CURRENCY);


        //when
        CardCommand command = converter.convert(card);

        //then
        assertNotNull(card);
        assertEquals(ID_VALUE, command.getId());
        assertEquals(HOLDERS_NAME, command.getHoldersName());
        assertEquals(CARD_TYPE, command.getCardType());
        assertEquals(CURRENCY, command.getCurrency());
    }
}