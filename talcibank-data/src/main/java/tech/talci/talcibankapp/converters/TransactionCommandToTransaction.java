package tech.talci.talcibankapp.converters;

import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import tech.talci.talcibankapp.commands.TransactionCommand;
import tech.talci.talcibankapp.domain.Transaction;

public class TransactionCommandToTransaction implements Converter<TransactionCommand, Transaction> {

    @Nullable
    @Synchronized
    @Override
    public Transaction convert(TransactionCommand transactionCommand) {
        return null;
    }
}
