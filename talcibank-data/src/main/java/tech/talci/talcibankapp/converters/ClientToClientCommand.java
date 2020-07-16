package tech.talci.talcibankapp.converters;

import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import tech.talci.talcibankapp.commands.ClientCommand;
import tech.talci.talcibankapp.domain.Client;

public class ClientToClientCommand implements Converter<Client, ClientCommand> {

    @Nullable
    @Synchronized
    @Override
    public ClientCommand convert(Client client) {
        return null;
    }
}
