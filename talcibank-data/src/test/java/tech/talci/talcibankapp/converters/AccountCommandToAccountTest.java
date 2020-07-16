package tech.talci.talcibankapp.converters;

import org.junit.Before;
import org.junit.Test;
import tech.talci.talcibankapp.commands.AccountCommand;
import tech.talci.talcibankapp.domain.Account;

import static org.junit.Assert.*;

public class AccountCommandToAccountTest {

    public static final Long ID_VALUE = Long.valueOf(2L);
    public static final String NAME = "Test Name";
    public static final String CLIENTS_NAME = "Test Clients Name";

    AccountCommandToAccount converter;

    @Before
    public void setUp() throws Exception {
        converter = new AccountCommandToAccount();
    }

    @Test
    public void testNullObject() throws Exception{
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new AccountCommand()));
    }

    @Test
    public void convert() {
        //given
        AccountCommand command = new AccountCommand();
        command.setId(ID_VALUE);
        command.setClientsName(CLIENTS_NAME);
        command.setName(NAME);

        //when
        Account account = converter.convert()
    }
}