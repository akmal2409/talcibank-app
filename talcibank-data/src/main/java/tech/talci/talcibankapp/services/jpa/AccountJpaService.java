package tech.talci.talcibankapp.services.jpa;

import lombok.extern.slf4j.Slf4j;
import tech.talci.talcibankapp.commands.AccountCommand;
import tech.talci.talcibankapp.converters.AccountCommandToAccount;
import tech.talci.talcibankapp.converters.AccountToAccountCommand;
import tech.talci.talcibankapp.domain.Account;
import tech.talci.talcibankapp.domain.AccountType;
import tech.talci.talcibankapp.domain.Client;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import tech.talci.talcibankapp.repositories.AccountRepository;
import tech.talci.talcibankapp.services.AccountService;
import tech.talci.talcibankapp.services.ClientService;


import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@Profile({"default", "jpa"})
@Transactional
public class AccountJpaService extends AbstractJpaService<Account, AccountRepository>
        implements AccountService {

    private final AccountToAccountCommand accountToAccountCommand;
    private final AccountCommandToAccount accountCommandToAccount;
    private Long accountNumber = 2000001l;
    ClientService clientService;

    public AccountJpaService(AccountRepository repository, ClientService clientService) {
        super(repository);
        this.accountToAccountCommand = new AccountToAccountCommand();
        this.accountCommandToAccount = new AccountCommandToAccount(clientService);
        this.clientService = clientService;
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

    @Override
    public Account findByAccountId(Long id) {
        return super.findById(id);
    }

    @Override
    @Transactional
    public AccountCommand saveAccountCommand(AccountCommand command) {
        Account detachedAccount = accountCommandToAccount.convert(command);

        detachedAccount.setNumber(accountNumber);
        accountNumber++;
        Account savedAccount = repository.save(detachedAccount);
        log.debug("Saved account id: " + savedAccount.getId());
        return accountToAccountCommand.convert(savedAccount);
    }
}
