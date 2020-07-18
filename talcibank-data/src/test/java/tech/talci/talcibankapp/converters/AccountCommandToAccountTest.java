package tech.talci.talcibankapp.converters;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import tech.talci.talcibankapp.commands.AccountCommand;
import tech.talci.talcibankapp.domain.Account;
import tech.talci.talcibankapp.domain.AccountType;
import tech.talci.talcibankapp.repositories.ClientRepository;
import tech.talci.talcibankapp.services.ClientService;
import tech.talci.talcibankapp.services.jpa.ClientJpaService;

import static org.junit.Assert.*;

public class AccountCommandToAccountTest {

    public static final Long ID_VALUE = Long.valueOf(2L);
    public static final String NAME = "Test Name";
    public static final AccountType ACCOUNT_TYPE = AccountType.PERSONAL;

    AccountCommandToAccount converter;
    ClientService clientService;
    @Mock
    ClientRepository clientRepository;

    @Before
    public void setUp() throws Exception {
        clientService = new ClientJpaService(clientRepository);
        converter = new AccountCommandToAccount(clientService);
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