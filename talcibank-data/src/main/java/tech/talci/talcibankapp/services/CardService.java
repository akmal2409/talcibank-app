package tech.talci.talcibankapp.services;

import tech.talci.talcibankapp.domain.Card;
import tech.talci.talcibankapp.domain.CardType;

import java.util.Set;

public interface CardService extends CrudService<Card, Long>{

    Card findByNumber(Long number);

    Set<Card> findByHoldersName(String holdersName);

    Set<Card> findByType(CardType type);
}
