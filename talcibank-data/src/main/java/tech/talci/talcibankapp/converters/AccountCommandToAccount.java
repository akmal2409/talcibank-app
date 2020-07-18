package tech.talci.talcibankapp.converters;

import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import tech.talci.talcibankapp.commands.AccountCommand;
import tech.talci.talcibankapp.domain.Account;
import tech.talci.talcibankapp.domain.Client;
import tech.talci.talcibankapp.repositories.AccountRepository;
import tech.talci.talcibankapp.services.AccountService;
import tech.talci.talcibankapp.services.ClientService;

public class AccountCommandToAccount implements Converter<AccountCommand, Account> {

    ClientService clientService;

    public AccountCommandToAccount(ClientService clientService) {
        this.clientService = clientService;
    }

    @Synchronized
    @Nullable
    @Override
    public Account convert(AccountCommand source) {

        if(source == null){
            return null;
        }

        Account account = new Account();
        account.setName(source.getName());
        account.setId(source.getId());
        account.setAccountType(source.getAccountType());
        if(source.getClientID() != null){
            Client client = clientService.findById(source.getClientID());
            if(client.getId() != null){
                account.setClient(client);
            }
        }
        return account;
    }
}
