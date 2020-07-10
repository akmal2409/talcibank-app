package services.jpa;

import domain.Account;
import domain.AccountType;
import domain.Client;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import repositories.AccountRepository;
import services.AccountService;


import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Profile("jpa")
@Transactional
public class AccountJpaService extends AbstractJpaService<Account, AccountRepository>
        implements AccountService {


    public AccountJpaService(AccountRepository repository) {
        super(repository);
    }

    @Override
    public Set<Account> findByAccountType(AccountType type) {
        return new HashSet<>(findByAccountType(type));
    }

    @Override
    public Account findByNumber(Long number) {
        return repository.findByNumber(number);
    }

    @Override
    public Account findByClient(Client client) {
        return repository.findByClient(client);
    }
}
