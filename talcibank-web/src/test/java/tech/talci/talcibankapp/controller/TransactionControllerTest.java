package tech.talci.talcibankapp.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tech.talci.talcibankapp.domain.Account;
import tech.talci.talcibankapp.domain.Client;
import tech.talci.talcibankapp.services.AccountService;
import tech.talci.talcibankapp.services.ClientService;
import tech.talci.talcibankapp.services.TransactionService;

import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TransactionControllerTest {

    MockMvc mockMvc;

    @Mock
    TransactionService transactionService;

    @Mock
    AccountService accountService;

    @Mock
    ClientService clientService;

    TransactionController controller;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        controller = new TransactionController(transactionService, clientService, accountService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetTransferForm() throws Exception{
        //given
        Client client = new Client();
        client.setId(1L);
        Account account = new Account();
        account.setId(3L);
        account.setClient(client);

        //when
        when(clientService.findById(anyLong())).thenReturn(client);

        //then
        mockMvc.perform(get("/client/1/transfer"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("client"))
                .andExpect(model().attributeExists("transaction"))
                .andExpect(view().name("client/account/transferForm"));

    }
}