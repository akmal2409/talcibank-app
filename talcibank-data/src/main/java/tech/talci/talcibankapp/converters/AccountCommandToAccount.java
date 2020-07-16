package tech.talci.talcibankapp.converters;

import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import tech.talci.talcibankapp.commands.AccountCommand;
import tech.talci.talcibankapp.domain.Account;

public class AccountCommandToAccount implements Converter<AccountCommand, Account> {

    @Synchronized
    @Nullable
    @Override
    public Account convert(AccountCommand accountCommand) {
        return null;
    }
}
