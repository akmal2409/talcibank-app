package tech.talci.talcibankapp.domain;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"sender", "recipient"})
@Builder
@Entity
@Table(name = "transactions")
public class Transaction{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Account sender;

    @Column(name = "recipient")
    private Long recipient;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private double amount;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;
}
