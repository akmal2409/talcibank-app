package tech.talci.talcibankapp.services.jpa;

import tech.talci.talcibankapp.commands.ClientCommand;
import tech.talci.talcibankapp.converters.ClientToClientCommand;
import tech.talci.talcibankapp.domain.Client;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import tech.talci.talcibankapp.repositories.ClientRepository;
import tech.talci.talcibankapp.services.ClientService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile({"default", "jpa"})
@Transactional
public class ClientJpaService extends AbstractJpaService<Client, ClientRepository> implements ClientService {

    private final ClientToClientCommand clientToClientCommand;

    public ClientJpaService(ClientRepository repository) {
        super(repository);
        clientToClientCommand = new ClientToClientCommand();
    }

    @Override
    public Set<Client> findByLastName(String lastName) {
        return new HashSet<>(repository.findByLastName(lastName));
    }

    @Override
    public Client findByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public ClientCommand findCommandById(Long id) {
        Optional<Client> clientOptional = repository.findById(id);

        if(clientOptional.isPresent()){
            Client client = clientOptional.get();

            return clientToClientCommand.convert(client);
        } else {
            return new ClientCommand();
        }
    }
}
