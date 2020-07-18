package tech.talci.talcibankapp.converters;

import org.junit.Before;
import org.junit.Test;
import tech.talci.talcibankapp.commands.AccountCommand;
import tech.talci.talcibankapp.domain.Account;
import tech.talci.talcibankapp.domain.AccountType;
import tech.talci.talcibankapp.domain.Client;

import static org.junit.Assert.*;

public class AccountToAccountCommandTest {

    public static final Long ID_VALUE = Long.valueOf(2L);
    public static final String NAME = "Test Name";
    public static final AccountType ACCOUNT_TYPE = AccountType.PERSONAL;

    AccountToAccountCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new AccountToAccountCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void convert() {
        //given
        Account account = new Account();
        account.setId(ID_VALUE);
        account.setName(NAME);
        account.setAccountType(ACCOUNT_TYPE);
        Client client = new Client();
        client.setId(2L);
        account.setClient(client);

        //when
        AccountCommand command = converter.convert(account);

        //then
        assertNotNull(command);
        assertEquals(ID_VALUE, command.getId());
        assertEquals(NAME, command.getName());
        assertEquals(ACCOUNT_TYPE, command.getAccountType());
    }
}