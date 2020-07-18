package tech.talci.talcibankapp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tech.talci.talcibankapp.commands.ClientCommand;
import tech.talci.talcibankapp.domain.Client;
import tech.talci.talcibankapp.repositories.ClientRepository;
import tech.talci.talcibankapp.services.ClientService;
import tech.talci.talcibankapp.services.jpa.ClientJpaService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ClientControllerTest {

    @Mock
    ClientService clientService;

    ClientController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new ClientController(clientService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getUserCabinet() throws Exception{
        //given
        Client client = new Client();
        client.setId(1L);

        //when
        when(clientService.findById(anyLong())).thenReturn(client);

        //then
        mockMvc.perform(get("/cabinet/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("client/cabinet"))
                .andExpect(model().attributeExists("client"));
    }

    @Test
    public void testShowTransactions() throws Exception{
        //given
        Client client = new Client();
        client.setId(2L);

        //when
        when(clientService.findById(anyLong())).thenReturn(client);

        //then
        mockMvc.perform(get("/cabinet/1/transactions"))
                .andExpect(status().isOk())
                .andExpect(view().name("client/transactions"))
                .andExpect(model().attributeExists("client"));
    }
}