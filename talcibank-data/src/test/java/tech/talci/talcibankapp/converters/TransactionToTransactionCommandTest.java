package tech.talci.talcibankapp.converters;

import org.junit.Before;
import org.junit.Test;
import tech.talci.talcibankapp.commands.AccountCommand;
import tech.talci.talcibankapp.commands.TransactionCommand;
import tech.talci.talcibankapp.domain.Account;
import tech.talci.talcibankapp.domain.Currency;
import tech.talci.talcibankapp.domain.Transaction;

import static org.junit.Assert.*;

public class TransactionToTransactionCommandTest {

    public static final Long ID_VALUE = Long.valueOf(2L);
    public static final Long SENDER_ID = Long.valueOf(3L);
    public static final Long RECIPIENT = Long.valueOf(44444L);
    public static final String DESCRIPTION = "description";
    public static final Double AMOUNT = Double.valueOf(20.0);
    public static final Currency CURRENCY = Currency.DOLLAR;

    TransactionToTransactionCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new TransactionToTransactionCommand(new AccountToAccountCommand());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Transaction()));
    }

    @Test
    public void convert() {
        //given
        Transaction transaction = new Transaction();
        transaction.setAmount(AMOUNT);
        transaction.setCurrency(CURRENCY);
        transaction.setDescription(DESCRIPTION);
        transaction.setId(ID_VALUE);
        transaction.setRecipient(RECIPIENT);
        Account account = new Account();
        account.setId(SENDER_ID);
        transaction.setSender(account);

        //when
        TransactionCommand command = converter.convert(transaction);

        //then
        assertNotNull(command);
        assertEquals(AMOUNT, command.getAmount());
        assertEquals(CURRENCY, command.getCurrency());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(ID_VALUE, command.getId());
        assertEquals(RECIPIENT, command.getRecipient());
        assertEquals(SENDER_ID, command.getSender().getId());
    }
}