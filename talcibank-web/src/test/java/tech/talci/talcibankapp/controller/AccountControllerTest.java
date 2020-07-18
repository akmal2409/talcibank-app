package tech.talci.talcibankapp.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tech.talci.talcibankapp.commands.AccountCommand;
import tech.talci.talcibankapp.commands.ClientCommand;
import tech.talci.talcibankapp.domain.Account;
import tech.talci.talcibankapp.domain.Client;
import tech.talci.talcibankapp.services.AccountService;
import tech.talci.talcibankapp.services.ClientService;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AccountControllerTest {

    @Mock
    AccountService accountService;

    @Mock
    ClientService clientService;

    AccountController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new AccountController(accountService, clientService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void createNewAccount() throws Exception{
        //given
        ClientCommand clientCommand = new ClientCommand();
        clientCommand.setId(1L);

        //when
        when(clientService.findCommandById(anyLong())).thenReturn(clientCommand);

        //then
        mockMvc.perform(get("/client/1/account/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("client/account/accountForm"))
                .andExpect(model().attributeExists("account"));
    }

    @Test
    public void saveOrUpdate() throws Exception{
        //given
        AccountCommand command = new AccountCommand();
        command.setId(2L);
        command.setClientID(4L);

        //when
        when(accountService.saveAccountCommand(any(AccountCommand.class)))
                .thenReturn(command);

        //then
        mockMvc.perform(post("/client/account")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("name", "name")
                .param("clientId", "id")
                .param("accountType", "PERSONAL"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/cabinet/" + command.getClientID()));
    }


}