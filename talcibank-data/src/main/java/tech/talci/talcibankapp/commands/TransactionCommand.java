package tech.talci.talcibankapp.commands;

import tech.talci.talcibankapp.domain.Currency;

public class TransactionCommand {

    private Long id;
    private AccountCommand sender;
    private Long recipient;
    private String description;
    private double amount;
    private Currency currency;
}
