package tech.talci.talcibankapp.services.jpa;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.talci.talcibankapp.domain.Account;
import tech.talci.talcibankapp.domain.Card;
import tech.talci.talcibankapp.domain.CardType;
import tech.talci.talcibankapp.repositories.CardRepository;
import tech.talci.talcibankapp.services.CardService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class CardJpaServiceTest {

    CardService cardService;

    @Mock
    CardRepository cardRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        cardService = new CardJpaService(cardRepository);
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
}