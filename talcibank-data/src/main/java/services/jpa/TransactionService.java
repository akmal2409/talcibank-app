package tech.talci.talcibank.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import tech.talci.talcibank.domain.Account;
import tech.talci.talcibank.domain.Transaction;
import tech.talci.talcibank.repositories.TransactionRepository;

import javax.transaction.Transactional;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
@Profile("jpa")
@Transactional
public class TransactionService extends AbstractJpaService<Transaction, TransactionRepository> implements tech.talci.talcibank.services.TransactionService {


    public TransactionService(TransactionRepository repository) {
        super(repository);
    }

    @Override
    public Set<Transaction> findByTimestamp(Timestamp timestamp) {
        return new HashSet<>(repository.findByTimestamp(timestamp));
    }

    @Override
    public Set<Transaction> findByRecipient(Long number) {
        return new HashSet<>(repository.findByRecipient(number));
    }

    @Override
    public Set<Transaction> findBySender(Long number) {
        return new HashSet<>(repository.findBySender(number));
    }
}
