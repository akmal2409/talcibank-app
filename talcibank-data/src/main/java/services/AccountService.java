package services;

import domain.Account;
import domain.AccountType;
import domain.Client;

import java.util.Set;

public interface AccountService extends CrudService<Account, Long>{

    Set<Account> findByAccountType(AccountType type);

    Account findByNumber(Long number);

    Account findByClient(Client client);
}
