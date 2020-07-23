package tech.talci.talcibankapp.services.jpa;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.talci.talcibankapp.commands.CardCommand;
import tech.talci.talcibankapp.domain.Account;
import tech.talci.talcibankapp.domain.Card;
import tech.talci.talcibankapp.domain.CardType;
import tech.talci.talcibankapp.domain.Client;
import tech.talci.talcibankapp.repositories.CardRepository;
import tech.talci.talcibankapp.services.CardService;
import tech.talci.talcibankapp.services.ClientService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class CardJpaServiceTest {

    CardService cardService;
    @Mock
    ClientService clientService;

    @Mock
    CardRepository cardRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        cardService = new CardJpaService(cardRepository, clientService);
    }

    @Test
    public void findByNumber() {
        //given
        Card testCard = new Card();
        testCard.setId(2L);
        Optional<Card> optionalCard = Optional.of(testCard);

        //when
        when(cardRepository.findByNumber(anyLong())).thenReturn(optionalCard.orElse(null));

        //then
        Card returnedCard = cardService.findByNumber(23L);
        assertNotNull(returnedCard);
        verify(cardRepository, times(1)).findByNumber(anyLong());
    }

    @Test
    public void findByHoldersName() {
        //given
        Card testCard = new Card();
        testCard.setId(2L);
        Set<Card> cardSet = new HashSet<>();
        cardSet.add(testCard);

        //when
        when(cardRepository.findByHoldersName(anyString())).thenReturn(cardSet);

        //then
        Set<Card> returnedCards = cardService.findByHoldersName("Test");
        assertEquals(1, returnedCards.size());
        verify(cardRepository, timeout(1)).findByHoldersName(anyString());
    }

    @Test
    public void findByCardType() {
        //given
        Card testCard = new Card();
        testCard.setId(2L);
        Set<Card> cardSet = new HashSet<>();
        cardSet.add(testCard);

        //when
        when(cardRepository.findByCardType(any())).thenReturn(cardSet);

        //then
        Set<Card> returnedCards = cardService.findByCardType(CardType.AMEX);
        assertEquals(1, returnedCards.size());
        verify(cardRepository, timeout(1)).findByCardType(any());
    }

    @Test
    public void testFindByCommandById() {
        //given
        Card card = new Card();
        card.setId(2L);
        Client client = new Client();
        client.setId(3L);
        card.setClient(client);

        Optional<Card> cardOptional = Optional.of(card);

        //when
        when(cardRepository.findById(anyLong())).thenReturn(cardOptional);

        //then
        CardCommand cardCommand = cardService.findCommandById(2l);

        assertEquals(card.getId(), cardCommand.getId());
        assertEquals(card.getClient().getId(), cardCommand.getClientID());
        verify(cardRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testSaveCardCommand() {
        //given
        Card card = new Card();
        card.setId(2L);
        Client client = new Client();
        client.setId(3L);
        card.setClient(client);

        //when
        when(cardRepository.save(any())).thenReturn(card);

        //then
        CardCommand returnedCard = cardService.saveCardCommand(new CardCommand());
        assertEquals(card.getId(), returnedCard.getId());
        verify(cardRepository, times(1)).save(any());

    }
}