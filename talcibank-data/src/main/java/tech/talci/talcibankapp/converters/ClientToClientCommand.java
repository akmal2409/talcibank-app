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
    public ClientCommand convert(Client source) {

        if(source == null){
            return null;
        }

        ClientCommand command = new ClientCommand();
        command.setBirthDate(source.getBirthDate());
        command.setPhoneNumber(source.getPhoneNumber());
        command.setAddress(source.getAddress());
        command.setSecretPhrase(source.getSecretPhrase());
        command.setPassword(source.getPassword());
        command.setId(source.getId());
        command.setEmail(source.getEmail());
        command.setFirstName(source.getFirstName());
        command.setLastName(source.getLastName());
        return command;
    }
}
