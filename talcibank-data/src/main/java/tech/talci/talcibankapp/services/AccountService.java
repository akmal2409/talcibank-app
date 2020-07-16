package tech.talci.talcibankapp.services;

import tech.talci.talcibankapp.domain.Account;
import tech.talci.talcibankapp.domain.AccountType;
import tech.talci.talcibankapp.domain.Client;

import java.util.Optional;
import java.util.Set;

public interface AccountService extends CrudService<Account, Long>{

    Set<Account> findByAccountType(AccountType type);

    Account findByNumber(Long number);

    Account findByClient(Client client);
}
