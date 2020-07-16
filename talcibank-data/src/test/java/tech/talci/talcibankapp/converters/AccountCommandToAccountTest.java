package tech.talci.talcibankapp.converters;

import org.junit.Before;
import org.junit.Test;
import tech.talci.talcibankapp.commands.AccountCommand;
import tech.talci.talcibankapp.domain.Account;
import tech.talci.talcibankapp.domain.AccountType;

import static org.junit.Assert.*;

public class AccountCommandToAccountTest {

    public static final Long ID_VALUE = Long.valueOf(2L);
    public static final String NAME = "Test Name";
    public static final AccountType ACCOUNT_TYPE = AccountType.PERSONAL;

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
        command.setName(NAME);
        command.setAccountType(ACCOUNT_TYPE);

        //when
        Account account = converter.convert(command);

        //then
        assertNotNull(account);
        assertEquals(ID_VALUE, account.getId());
        assertEquals(NAME, account.getName());
        assertEquals(ACCOUNT_TYPE, account.getAccountType());
    }
}