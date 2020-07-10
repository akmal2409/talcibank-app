package tech.talci.talcibank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.talci.talcibank.domain.Card;
import tech.talci.talcibank.domain.CardType;

import java.util.Set;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Card findByNumber(Long number);

    Set<Card> findByHoldersName(String holdersName);

    Set<Card> findByCardType(CardType type);
}
