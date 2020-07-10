package services;

import tech.talci.talcibank.domain.Account;
import tech.talci.talcibank.domain.AccountType;
import tech.talci.talcibank.domain.Card;
import tech.talci.talcibank.domain.Client;

import java.util.Set;

public interface AccountService extends CrudService<Account, Long>{

    Set<Account> findByAccountType(AccountType type);

    Account findByNumber(Long number);

    Account findByClient(Client client);
}
