package tech.talci.talcibankapp.controller;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tech.talci.talcibankapp.commands.ClientCommand;
import tech.talci.talcibankapp.domain.Client;
import tech.talci.talcibankapp.repositories.ClientRepository;
import tech.talci.talcibankapp.services.ClientService;
import tech.talci.talcibankapp.services.jpa.ClientJpaService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class ClientControllerTest {

    @Mock
    ClientService clientService;

    ClientController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        controller = new ClientController(clientService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getUserCabinet() throws Exception{
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

    @Test
    public void testGetWithdrawalHistory() throws Exception{
        //given
        Client client = new Client();
        client.setId(1L);

        //when
        when(clientService.findById(anyLong())).thenReturn(client);

        //then
        mockMvc.perform(get("/cabinet/1/withdrawals"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("client"))
                .andExpect(view().name("client/account/withdrawalHistory"));

        verify(clientService, times(1)).findById(anyLong());
    }

    @Test
    public void testGetDepositHistory() throws Exception{
        //given
        Client client = new Client();
        client.setId(1L);

        //when
        when(clientService.findById(anyLong())).thenReturn(client);

        //then
        mockMvc.perform(get("/cabinet/1/deposits"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("client"))
                .andExpect(view().name("client/account/depositHistory"));

        verify(clientService, times(1)).findById(anyLong());
    }
}