package tech.talci.talcibank.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import tech.talci.talcibank.domain.Card;
import tech.talci.talcibank.domain.CardType;
import tech.talci.talcibank.repositories.CardRepository;
import tech.talci.talcibank.services.CardService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Profile("jpa")
@Transactional
public class CardJpaService extends AbstractJpaService<Card, CardRepository> implements CardService {


    public CardJpaService(CardRepository repository) {
        super(repository);
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
    public Set<Card> findByType(CardType type) {
        return new HashSet<>(repository.findByCardType(type));
    }
}
