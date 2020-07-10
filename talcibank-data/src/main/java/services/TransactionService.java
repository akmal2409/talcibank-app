package services;

import domain.Transaction;
import java.sql.Timestamp;
import java.util.Set;

public interface TransactionService extends CrudService<Transaction, Long> {

    Set<Transaction> findByTimestamp(Timestamp timestamp);

    Set<Transaction> findByRecipient(Long number);

    Set<Transaction> findBySender(Long number);
}
