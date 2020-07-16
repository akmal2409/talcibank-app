package tech.talci.talcibankapp.services.jpa;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import tech.talci.talcibankapp.domain.Account;
import tech.talci.talcibankapp.domain.Transaction;
import tech.talci.talcibankapp.repositories.TransactionRepository;
import tech.talci.talcibankapp.services.TransactionService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class TransactionJpaServiceTest {

    @Mock
    TransactionRepository transactionRepository;

    TransactionService transactionService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        transactionService = new TransactionJpaService(transactionRepository);
    }

    @Test
    public void findByTimestamp() {
        //given
        Transaction testTransaction = new Transaction();
        Set<Transaction> transactionSet = new HashSet<>();
        transactionSet.add(testTransaction);

        //when
        when(transactionRepository.findByTimestamp(any())).thenReturn(transactionSet);
        Set<Transaction> returnedSet = transactionService.findByTimestamp(Timestamp.valueOf(LocalDateTime.now()));

        //then
        assertEquals(1, returnedSet.size());
        verify(transactionRepository, times(1)).findByTimestamp(any());
    }

    @Test
    public void findByRecipient() {
        //given
        Transaction testTransaction = new Transaction();
        Set<Transaction> transactionSet = new HashSet<>();
        transactionSet.add(testTransaction);

        //when
        when(transactionRepository.findByRecipient(anyLong())).thenReturn(transactionSet);
        Set<Transaction> returnedSet = transactionService.findByRecipient(23123L);

        //then
        assertEquals(1, returnedSet.size());
        verify(transactionRepository, timeout(1)).findByRecipient(anyLong());
    }

    @Test
    public void findBySender() {
        //given
        Transaction testTransaction = new Transaction();
        Set<Transaction> transactionSet = new HashSet<>();
        transactionSet.add(testTransaction);

        //when
        when(transactionRepository.findBySender(any())).thenReturn(transactionSet);
        Set<Transaction> returnedSet = transactionService.findBySender(new Account());

        //then
        assertEquals(1, returnedSet.size());
        verify(transactionRepository,times(1)).findBySender(any());
    }
}