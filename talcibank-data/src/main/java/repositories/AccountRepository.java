package tech.talci.talcibank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.talci.talcibank.domain.Account;
import tech.talci.talcibank.domain.AccountType;
import tech.talci.talcibank.domain.Card;
import tech.talci.talcibank.domain.Client;

import java.util.Set;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Set<Account> findByAccountType(AccountType type);

    Account findByNumber(Long number);

    Account findByClient(Client client);
}
