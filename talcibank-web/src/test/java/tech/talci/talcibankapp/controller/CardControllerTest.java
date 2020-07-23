package tech.talci.talcibankapp.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tech.talci.talcibankapp.commands.CardCommand;
import tech.talci.talcibankapp.domain.Card;
import tech.talci.talcibankapp.domain.Client;
import tech.talci.talcibankapp.services.CardService;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CardControllerTest {

    @Mock
    CardService cardService;

    MockMvc mockMvc;

    CardController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new CardController(cardService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void saveNewCard() throws Exception{
        //given
        CardCommand cardCommand = new CardCommand();
        cardCommand.setId(3L);
        cardCommand.setClientID(2L);

        //when
        when(cardService.saveCardCommand(any())).thenReturn(cardCommand);

        //then
        mockMvc.perform(post("/client/card")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("id", "2")
        .param("holdersName", "23")
        .param("clientId", "2")
        .param("cardType", "")
        .param("currency", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/cabinet/2"));

        verify(cardService, times(1)).saveCardCommand(any());
    }

    @Test
    public void showCardForm() throws Exception{
        //given
        CardCommand command = new CardCommand();
        command.setClientID(2L);
        command.setId(3L);

        //when
        when(cardService.findCommandById(anyLong())).thenReturn(command);

        //then
        mockMvc.perform(get("/client/2/cards/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("client/card/cardForm"))
                .andExpect(model().attributeExists("card"));
    }

    @Test
    public void testDeleteCard() throws Exception{
        //when
        mockMvc.perform(get("/client/1/card/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/cabinet/1"));

        verify(cardService, times(1)).deleteById(anyLong());
    }
}