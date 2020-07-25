package tech.talci.talcibankapp.validators;

import tech.talci.talcibankapp.domain.Account;

public class WithdrawalValidator {

    public boolean validate(Account account, double amount){
        return (account.getBalance() - amount) > 0 && amount > 0;
    }
}
