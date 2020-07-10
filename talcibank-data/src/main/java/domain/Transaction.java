package domain;


import lombok.*;
import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"sender", "recipient"})
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity{

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Account sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Account recipient;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private double amount;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;
}
