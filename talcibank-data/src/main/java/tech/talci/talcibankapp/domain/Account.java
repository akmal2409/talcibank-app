package tech.talci.talcibankapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"client", "transactions"})
@NoArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "account_number")
    private Long number;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "balance")
    private double balance;

    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;

    @OneToMany
    private Set<Transaction> transactions = new HashSet<>();


    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }
}
