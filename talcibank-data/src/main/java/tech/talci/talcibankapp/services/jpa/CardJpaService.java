package tech.talci.talcibankapp.services.jpa;

import lombok.extern.slf4j.Slf4j;
import tech.talci.talcibankapp.commands.CardCommand;
import tech.talci.talcibankapp.converters.CardCommandToCard;
import tech.talci.talcibankapp.converters.CardToCardCommand;
import tech.talci.talcibankapp.domain.Card;
import tech.talci.talcibankapp.domain.CardType;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import tech.talci.talcibankapp.repositories.CardRepository;
import tech.talci.talcibankapp.services.CardService;


import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@Profile({"default", "jpa"})
@Transactional
public class CardJpaService extends AbstractJpaService<Card, CardRepository> implements CardService {

    CardToCardCommand cardToCardCommand;
    CardCommandToCard cardCommandToCard;

    private Long number = 4023080709087656L;

    public CardJpaService(CardRepository repository) {
        super(repository);
        cardToCardCommand = new CardToCardCommand();
        cardCommandToCard = new CardCommandToCard();
    }

    @Override
    public Card findByNumber(Long number) {
        return repository.findByNumber(number);
    }

    @Override
    public Set<Card> findByHoldersName(String holdersName) {
        return new HashSet<>(repository.findByHoldersName(holdersName));
    }

    @Override
    public Set<Card> findByCardType(CardType type) {
        return new HashSet<>(repository.findByCardType(type));
    }

    @Override
    public CardCommand findCommandById(Long id) {
        Optional<Card> cardOptional = repository.findById(id);

        if(cardOptional.isPresent()){

            Card card = cardOptional.get();

            return cardToCardCommand.convert(card);
        }
        return new CardCommand();
    }

    @Override
    public CardCommand saveCardCommand(CardCommand cardCommand) {

        Card detachedCard = cardCommandToCard.convert(cardCommand);
        detachedCard.setNumber(number);
        number++;

        Card savedCard = repository.save(detachedCard);
        log.debug("Saved card id : " + cardCommand.getId());

        return cardToCardCommand.convert(savedCard);
    }
}
