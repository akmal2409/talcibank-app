package tech.talci.talcibankapp.services.jpa;

import tech.talci.talcibankapp.domain.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import tech.talci.talcibankapp.repositories.TransactionRepository;
import tech.talci.talcibankapp.services.TransactionService;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Service
@Profile({"default", "jpa"})
@Transactional
public class TransactionJpaService extends AbstractJpaService<Transaction, TransactionRepository> implements TransactionService {


    public TransactionJpaService(TransactionRepository repository) {
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
    public Set<Transaction> findBySender(Account sender) {
        return new HashSet<>(repository.findBySender(sender));
    }
}
