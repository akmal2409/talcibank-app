package services.jpa;

import domain.Transaction;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import repositories.TransactionRepository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Service
@Profile("jpa")
@Transactional
public class TransactionService extends AbstractJpaService<Transaction, TransactionRepository> implements services.TransactionService {


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
