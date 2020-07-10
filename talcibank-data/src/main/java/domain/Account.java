package domain;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account extends BaseEntity{


    private String name;


    private Long number;


    private Client client;


    private double balance;


    private AccountType accountType;


    private Set<Transaction> transactions = new HashSet<>();


    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }
}
