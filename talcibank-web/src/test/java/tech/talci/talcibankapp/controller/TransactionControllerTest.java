package tech.talci.talcibankapp.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tech.talci.talcibankapp.domain.Account;
import tech.talci.talcibankapp.domain.Client;
import tech.talci.talcibankapp.services.*;

import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.*;
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

    @Mock
    WithdrawalService withdrawalService;

    @Mock
    DepositService depositService;

    TransactionController controller;

    Client client;
    Account account;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        controller = new TransactionController(transactionService, clientService, accountService,
                                withdrawalService, depositService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        client = new Client();
        client.setId(1L);
        account = new Account();
        account.setId(3L);
        account.setBalance(900.0);
        account.setClient(client);
    }

    @Test
    public void testGetTransferForm() throws Exception{
        //when
        when(accountService.findById(anyLong())).thenReturn(account);

        //then
        mockMvc.perform(get("/client/1/account/3/transfer"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("transaction"))
                .andExpect(view().name("client/account/transferForm"));

        verify(accountService, times(1)).findById(anyLong());

    }

    @Test
    public void testProcessTransferForm() throws Exception{
        //when
        when(accountService.findById(anyLong())).thenReturn(account);

        //then
        mockMvc.perform(post("/client/1/account/3/transfer")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("amount", "10")
                .param("recipient", "2312323"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/cabinet/1"));

        verify(accountService, times(1)).findById(anyLong());
    }

    @Test
    public void testGetWithdrawalForm() throws Exception{
        //when
        when(accountService.findById(anyLong())).thenReturn(account);

        //then
        mockMvc.perform(get("/client/1/account/3/withdraw"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("withdrawal"))
                .andExpect(view().name("client/account/withdrawalForm"));

        verify(accountService, times(1)).findById(anyLong());

    }

    @Test
    public void testProcessWithdrawalForm() throws Exception{
        //when
        when(accountService.findById(anyLong())).thenReturn(account);

        //then
        mockMvc.perform(post("/client/1/account/3/withdraw")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("amount", "10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/cabinet/1"));

        verify(accountService, times(1)).findById(anyLong());
    }

    @Test
    public void testGetDepositForm() throws Exception{
        //when
        when(accountService.findById(anyLong())).thenReturn(account);

        //then
        mockMvc.perform(get("/client/1/account/3/deposit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("deposit"))
                .andExpect(view().name("client/account/depositForm"));

        verify(accountService, times(1)).findById(anyLong());
    }


    @Test
    public void testProcessDepositForm() throws Exception{
        //when
        when(accountService.findById(anyLong())).thenReturn(account);

        //then
        mockMvc.perform(post("/client/1/account/3/deposit")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("amount", "10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/cabinet/1"));

        verify(accountService, times(1)).findById(anyLong());
    }

}