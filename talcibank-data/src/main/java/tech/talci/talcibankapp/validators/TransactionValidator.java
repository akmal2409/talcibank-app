package tech.talci.talcibankapp.validators;

import tech.talci.talcibankapp.domain.Account;
import tech.talci.talcibankapp.services.AccountService;

public class TransactionValidator {

    public boolean validate(Account accountFrom, double amount){
        return (accountFrom.getBalance() - amount) > 0 && amount > 0;
    }

}
