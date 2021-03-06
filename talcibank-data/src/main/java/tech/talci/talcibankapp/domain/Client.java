package tech.talci.talcibankapp.domain;

import lombok.*;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"cards", "accounts"})
@Builder
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Lob
    private Byte[] image;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "secret_phrase")
    private String secretPhrase;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "active")
    private boolean active = false;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private Set<Card> cards = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private Set<Account> accounts = new HashSet<>();


    public void addAccountTo(Account account){
        account.setClient(this);
        this.accounts.add(account);
    }

    public void addCardTo(Card card){
        card.setClient(this);
        this.cards.add(card);
    }

    @PrePersist
    public void createdAt(){
        this.createdAt = new Date();
    }
}
