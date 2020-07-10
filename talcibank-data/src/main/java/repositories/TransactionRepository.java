package tech.talci.talcibank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.talci.talcibank.domain.Account;
import tech.talci.talcibank.domain.Transaction;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Set<Transaction> findByTimestamp(Timestamp timestamp);

    Set<Transaction> findByRecipient(Long number);

    Set<Transaction> findBySender(Long numebr);
}
