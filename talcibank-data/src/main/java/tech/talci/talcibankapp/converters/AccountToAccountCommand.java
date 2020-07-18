package tech.talci.talcibankapp.converters;

import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import tech.talci.talcibankapp.commands.AccountCommand;
import tech.talci.talcibankapp.domain.Account;
import tech.talci.talcibankapp.services.ClientService;

public class AccountToAccountCommand implements Converter<Account, AccountCommand> {

    @Synchronized
    @Nullable
    @Override
    public AccountCommand convert(Account source) {

        if(source == null){
            return null;
        }

        AccountCommand command = new AccountCommand();
        command.setAccountType(source.getAccountType());
        command.setName(source.getName());
        command.setId(source.getId());

        if(source.getClient().getId() != null){
            command.setClientID(source.getClient().getId());
        }

        return command;
    }
}
