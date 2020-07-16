package tech.talci.talcibankapp.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"client"})
@Builder
@Entity
@Table
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "account_number")
    private Long number;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "holders_name")
    private String holdersName;

    @Column(name = "issued")
    private LocalDate issued;

    @Column(name = "expires")
    private LocalDate expires;

    @Enumerated(value = EnumType.STRING)
    private CardType cardType;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    @Column(name = "balance")
    private Double balance;


    public void setHoldersName(String name){
        holdersName = name;
    }
}
