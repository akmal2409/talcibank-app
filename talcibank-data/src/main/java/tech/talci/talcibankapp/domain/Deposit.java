package tech.talci.talcibankapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"account"})
@Entity
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double amount;
    private Date date;

    @ManyToOne
    private Account account;

    @Enumerated(value = EnumType.STRING)
    private DepositType depositType;
}
