package tech.talci.talcibankapp.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"client", "transactions"})
@Builder
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "account_number")
    private Long number;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "balance")
    private double balance;

    @Column(name = "created_at")
    private Date createdAt;

    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Transaction> transactions = new HashSet<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Withdrawal> withdrawals = new HashSet<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Deposit> deposits = new HashSet<>();


    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    @PrePersist
    public void createdAt(){
        this.createdAt = new Date();
    }
}
