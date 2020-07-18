package tech.talci.talcibankapp.services.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.talci.talcibankapp.commands.AccountCommand;
import tech.talci.talcibankapp.domain.Account;
import tech.talci.talcibankapp.domain.AccountType;
import tech.talci.talcibankapp.domain.Client;
import tech.talci.talcibankapp.repositories.AccountRepository;
import tech.talci.talcibankapp.services.AccountService;
import tech.talci.talcibankapp.services.ClientService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AccountJpaServiceTest {

    @Mock
    AccountRepository accountRepository;

    AccountService accountService;

    @Mock
    ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        accountService = new AccountJpaService(accountRepository, clientService);
    }

    @Test
    void findByAccountType() {
        //given
        Account testAccount = new Account();
        testAccount.setId(2L);
        testAccount.setAccountType(AccountType.PERSONAL);
        Set<Account> accountList = new HashSet<>();
        accountList.add(testAccount);

        //when
        when(accountRepository.findByAccountType(any())).thenReturn(accountList);
        //then
        Set<Account> accounts = accountService.findByAccountType(AccountType.PERSONAL);

        assertEquals(1, accounts.size());
        verify(accountRepository, times(1)).findByAccountType(any());
    }

    @Test
    void findByNumber() {
        //given
        Account testAccount = new Account();
        testAccount.setId(2L);
        Optional<Account> accountOptional = Optional.of(testAccount);

        //when
        when(accountRepository.findByNumber(anyLong())).thenReturn(accountOptional.orElse(null));

        //then
        Account returnedAccount = accountService.findByNumber(3L);

        assertNotNull(returnedAccount);
        verify(accountRepository, times(1)).findByNumber(anyLong());
    }

    @Test
    void findByClient() {
        //given
        Account testAccount = new Account();
        testAccount.setId(2L);
        Optional<Account> accountOptional = Optional.of(testAccount);

        //when
        when(accountRepository.findByClient(any())).thenReturn(accountOptional.orElse(null));

        //then
        Account returnedAccount = accountService.findByClient(new Client());
        assertNotNull(returnedAccount);
        verify(accountRepository, times(1)).findByClient(any());
    }

}