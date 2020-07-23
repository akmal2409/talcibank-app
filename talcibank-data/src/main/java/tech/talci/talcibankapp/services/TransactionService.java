package tech.talci.talcibankapp.services;

import tech.talci.talcibankapp.domain.Account;
import tech.talci.talcibankapp.domain.Transaction;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public interface TransactionService extends CrudService<Transaction, Long> {

    Set<Transaction> findByTimestamp(Timestamp timestamp);

    Set<Transaction> findByRecipient(Long number);

    Set<Transaction> findBySender(Account number);

    Set<Transaction> readTransactionsBySenderAndTimestampBetween(
            Account sender, Timestamp startDate, Timestamp endDate);
}
