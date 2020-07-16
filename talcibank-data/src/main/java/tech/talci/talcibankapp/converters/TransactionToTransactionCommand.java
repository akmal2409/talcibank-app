package tech.talci.talcibankapp.converters;

import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import tech.talci.talcibankapp.commands.TransactionCommand;
import tech.talci.talcibankapp.domain.Transaction;


public class TransactionToTransactionCommand implements Converter<Transaction, TransactionCommand> {

    private final AccountToAccountCommand accountConverter;

    public TransactionToTransactionCommand(AccountToAccountCommand accountConverter) {
        this.accountConverter = accountConverter;
    }

    @Nullable
    @Synchronized
    @Override
    public TransactionCommand convert(Transaction source) {

        if(source == null){
            return null;
        }

        TransactionCommand command = new TransactionCommand();
        command.setSender(accountConverter.convert(source.getSender()));
        command.setRecipient(source.getRecipient());
        command.setId(source.getId());
        command.setDescription(source.getDescription());
        command.setCurrency(source.getCurrency());
        command.setAmount(source.getAmount());
        return command;
    }
}
