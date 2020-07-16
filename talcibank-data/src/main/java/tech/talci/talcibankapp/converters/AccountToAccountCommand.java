package tech.talci.talcibankapp.converters;

import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import tech.talci.talcibankapp.commands.AccountCommand;
import tech.talci.talcibankapp.domain.Account;

public class AccountToAccountCommand implements Converter<Account, AccountCommand> {

    @Synchronized
    @Nullable
    @Override
    public AccountCommand convert(Account account) {
        return null;
    }
}
