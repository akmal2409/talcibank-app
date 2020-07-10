package domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction extends BaseEntity{

    private Timestamp timestamp;


    private Account sender;


    private Account recipient;


    private String description;


    private double amount;

    private Currency currency;
}
