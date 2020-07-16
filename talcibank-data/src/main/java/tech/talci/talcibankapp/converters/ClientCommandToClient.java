package tech.talci.talcibankapp.converters;

import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import tech.talci.talcibankapp.commands.ClientCommand;
import tech.talci.talcibankapp.domain.Client;

public class ClientCommandToClient implements Converter<ClientCommand, Client> {

    @Nullable
    @Synchronized
    @Override
    public Client convert(ClientCommand clientCommand) {
        return null;
    }
}
