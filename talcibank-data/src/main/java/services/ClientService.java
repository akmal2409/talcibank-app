package services;

import domain.Client;

import java.util.Set;

public interface ClientService extends CrudService<Client, Long> {

    Set<Client> findByLastName(String lastName);

    Client findByPhoneNumber(String phoneNumber);
}
