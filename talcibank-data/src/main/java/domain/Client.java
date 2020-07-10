package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client extends BaseEntity{


    private String firstName;


    private String lastName;


    private String address;


    private String phoneNumber;


    private LocalDate birthDate;

    private String secretPhrase;


    private Set<Card> cards = new HashSet<>();


    private Set<Account> accounts = new HashSet<>();


    public void addAccountTo(Account account){
        account.setClient(this);
        this.accounts.add(account);
    }

    public void addCardTo(Card card){
        card.setClient(this);
        this.cards.add(card);
    }
}
