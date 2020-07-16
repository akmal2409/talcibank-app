package tech.talci.talcibankapp.converters;

import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import tech.talci.talcibankapp.commands.AccountCommand;
import tech.talci.talcibankapp.domain.Account;
import tech.talci.talcibankapp.repositories.AccountRepository;
import tech.talci.talcibankapp.services.AccountService;
import tech.talci.talcibankapp.services.ClientService;

public class AccountCommandToAccount implements Converter<AccountCommand, Account> {



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
        return account;
    }
}
