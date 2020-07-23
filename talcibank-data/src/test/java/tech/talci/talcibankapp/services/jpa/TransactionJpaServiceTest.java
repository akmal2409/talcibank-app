package tech.talci.talcibankapp.services.jpa;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import tech.talci.talcibankapp.domain.Account;
import tech.talci.talcibankapp.domain.Transaction;
import tech.talci.talcibankapp.repositories.TransactionRepository;
import tech.talci.talcibankapp.services.TransactionService;

import java.sql.Time;
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

    @Test
    public void testReadTransactionsBySenderAndTimestampBetween(){
        //given
        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();
        Transaction transaction3 = new Transaction();

        transaction1.setId(1L);
        transaction2.setId(2L);
        transaction1.setId(3L);

        Account sender = new Account();
        sender.setId(2L);

        transaction1.setSender(sender);
        transaction2.setSender(sender);
        transaction3.setSender(sender);

        transaction1.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        transaction2.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        transaction3.setTimestamp(Timestamp.valueOf(LocalDateTime.of(2019, 9, 9, 10,
                9)));

        Set<Transaction> transactionSet = new HashSet<>();
        transactionSet.add(transaction1);
        transactionSet.add(transaction2);
        transactionSet.add(transaction3);

        //when
        when(transactionRepository
                .readTransactionsBySenderAndTimestampBetween(any(Account.class), any(Timestamp.class),
                        any(Timestamp.class))).thenReturn(transactionSet);

        //then
        Set<Transaction> returnedTransactions = transactionService.readTransactionsBySenderAndTimestampBetween(new Account(),
                Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));

        assertEquals(3, returnedTransactions.size());
        verify(transactionRepository, times(1)).readTransactionsBySenderAndTimestampBetween(any(),
                any(), any());

    }
}