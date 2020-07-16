package tech.talci.talcibankapp.services.jpa;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import tech.talci.talcibankapp.domain.Client;
import tech.talci.talcibankapp.repositories.CardRepository;
import tech.talci.talcibankapp.repositories.ClientRepository;
import tech.talci.talcibankapp.services.ClientService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ClientJpaServiceTest {

    @Mock
    ClientRepository clientRepository;


    ClientService clientService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        clientService = new ClientJpaService(clientRepository);
    }

    @Test
    public void findByLastName() {
        //given
        Client testClient = new Client();
        testClient.setId(2L);
        Set<Client> clientSet = new HashSet<>();
        clientSet.add(testClient);

        // when
        when(clientRepository.findByLastName(anyString())).thenReturn(clientSet);

        //then
        Set<Client> returnedClients = clientService.findByLastName("Test");
        assertEquals(1, returnedClients.size());
        verify(clientRepository, times(1)).findByLastName(anyString());
    }

    @Test
    public void findByPhoneNumber() {
        //given
        Client testClient = new Client();
        Long id = Long.valueOf(2L);
        testClient.setId(id);
        Optional<Client> optionalClient = Optional.of(testClient);

        //when
        when(clientRepository.findByPhoneNumber(anyString())).thenReturn(optionalClient.get());
        Client returnedClient = clientService.findByPhoneNumber("test");

        //then
        assertNotNull(returnedClient);
        assertEquals(id, returnedClient.getId());
        verify(clientRepository, times(1)).findByPhoneNumber(anyString());
    }
}