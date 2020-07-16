package tech.talci.talcibankapp.converters;

import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import tech.talci.talcibankapp.commands.TransactionCommand;
import tech.talci.talcibankapp.domain.Transaction;

public class TransactionCommandToTransaction implements Converter<TransactionCommand, Transaction> {

    private final AccountCommandToAccount accountConverter;

    public TransactionCommandToTransaction(AccountCommandToAccount accountConverter) {
        this.accountConverter = accountConverter;
    }

    @Nullable
    @Synchronized
    @Override
    public Transaction convert(TransactionCommand source) {

        if(source == null){
            return null;
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(source.getAmount());
        transaction.setCurrency(source.getCurrency());
        transaction.setDescription(source.getDescription());
        transaction.setId(source.getId());
        transaction.setRecipient(source.getRecipient());
        transaction.setSender(accountConverter.convert(source.getSender()));
        return transaction;
    }
}
