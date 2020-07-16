package tech.talci.talcibankapp.converters;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.propertyeditors.CurrencyEditor;
import tech.talci.talcibankapp.commands.AccountCommand;
import tech.talci.talcibankapp.commands.TransactionCommand;
import tech.talci.talcibankapp.domain.Currency;
import tech.talci.talcibankapp.domain.Transaction;

import static org.junit.Assert.*;

public class TransactionCommandToTransactionTest {

    public static final Long ID_VALUE = Long.valueOf(2L);
    public static final Long SENDER_ID = Long.valueOf(3L);
    public static final Long RECIPIENT = Long.valueOf(44444L);
    public static final String DESCRIPTION = "description";
    public static final Double AMOUNT = Double.valueOf(20.0);
    public static final Currency CURRENCY = Currency.DOLLAR;

    TransactionCommandToTransaction converter;


    @Before
    public void setUp() throws Exception {
        converter = new TransactionCommandToTransaction(new AccountCommandToAccount());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new TransactionCommand()));
    }

    @Test
    public void convert() {
        //given
        TransactionCommand command = new TransactionCommand();
        command.setAmount(AMOUNT);
        command.setCurrency(CURRENCY);
        command.setDescription(DESCRIPTION);
        command.setId(ID_VALUE);
        command.setRecipient(RECIPIENT);
        AccountCommand accountCommand = new AccountCommand();
        accountCommand.setId(SENDER_ID);
        command.setSender(accountCommand);

        //when
        Transaction transaction = converter.convert(command);

        //then
        assertNotNull(transaction);
        assertEquals(AMOUNT, transaction.getAmount());
        assertEquals(CURRENCY, transaction.getCurrency());
        assertEquals(DESCRIPTION, transaction.getDescription());
        assertEquals(ID_VALUE, transaction.getId());
        assertEquals(RECIPIENT, transaction.getRecipient());
        assertEquals(SENDER_ID, transaction.getSender().getId());
    }
}