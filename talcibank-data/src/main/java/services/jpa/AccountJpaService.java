package tech.talci.talcibank.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import tech.talci.talcibank.domain.Account;
import tech.talci.talcibank.domain.AccountType;
import tech.talci.talcibank.domain.Client;
import tech.talci.talcibank.repositories.AccountRepository;
import tech.talci.talcibank.services.AccountService;

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
