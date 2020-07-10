package services.jpa;

import domain.Card;
import domain.CardType;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import repositories.CardRepository;
import services.CardService;


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
