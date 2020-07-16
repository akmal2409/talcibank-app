package tech.talci.talcibankapp.services.jpa;

import tech.talci.talcibankapp.domain.Account;
import tech.talci.talcibankapp.domain.AccountType;
import tech.talci.talcibankapp.domain.Client;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import tech.talci.talcibankapp.repositories.AccountRepository;
import tech.talci.talcibankapp.services.AccountService;


import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
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
        Set<Account> accounts = repository.findByAccountType(type);
        return accounts;
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
