package tech.talci.talcibank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.talci.talcibank.domain.Client;

import java.util.Set;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Set<Client> findByLastName(String lastName);

    Client findByPhoneNumber(String phoneNumber);
}
