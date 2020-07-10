package tech.talci.talcibank.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import tech.talci.talcibank.domain.Client;
import tech.talci.talcibank.repositories.ClientRepository;
import tech.talci.talcibank.services.ClientService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Profile("jpa")
@Transactional
public class ClientJpaService extends AbstractJpaService<Client, ClientRepository> implements ClientService {


    public ClientJpaService(ClientRepository repository) {
        super(repository);
    }

    @Override
    public Set<Client> findByLastName(String lastName) {
        return new HashSet<>(repository.findByLastName(lastName));
    }

    @Override
    public Client findByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }
}
