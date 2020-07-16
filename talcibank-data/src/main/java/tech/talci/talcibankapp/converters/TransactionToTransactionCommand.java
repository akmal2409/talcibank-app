package tech.talci.talcibankapp.converters;

import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import tech.talci.talcibankapp.commands.TransactionCommand;

import javax.transaction.Transaction;

public class TransactionToTransactionCommand implements Converter<Transaction, TransactionCommand> {

    @Nullable
    @Synchronized
    @Override
    public TransactionCommand convert(Transaction transaction) {
        return null;
    }
}
