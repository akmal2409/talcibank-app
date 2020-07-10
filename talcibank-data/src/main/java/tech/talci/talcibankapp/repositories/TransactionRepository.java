package tech.talci.talcibankapp.repositories;

import tech.talci.talcibankapp.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Set;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Set<Transaction> findByTimestamp(Timestamp timestamp);

    Set<Transaction> findByRecipient(Long number);

    Set<Transaction> findBySender(Long numebr);
}
