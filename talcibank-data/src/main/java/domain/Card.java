package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card extends BaseEntity{

    private Long number;

    private Client client;

    private String holdersName;

    private LocalDate issued;


    private LocalDate expires;


    private CardType cardType;


    private Currency currency;

    private Double balance;


    public void setHoldersName(String name){
        holdersName = name;
    }
}
