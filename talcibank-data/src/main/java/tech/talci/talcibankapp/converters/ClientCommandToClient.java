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
    public Client convert(ClientCommand source) {

        if(source == null){
            return null;
        }

        Client client = new Client();
        client.setId(source.getId());
        client.setAddress(source.getAddress());
        client.setBirthDate(source.getBirthDate());
        client.setEmail(source.getEmail());
        client.setPassword(source.getPassword());
        client.setFirstName(source.getFirstName());
        client.setLastName(source.getLastName());
        client.setPhoneNumber(source.getPhoneNumber());
        client.setSecretPhrase(source.getSecretPhrase());
        return client;
    }
}
