package tech.talci.talcibankapp.services;

import tech.talci.talcibankapp.domain.Client;

import java.util.Set;

public interface ClientService extends CrudService<Client, Long> {

    Set<Client> findByLastName(String lastName);

    Client findByPhoneNumber(String phoneNumber);
}
