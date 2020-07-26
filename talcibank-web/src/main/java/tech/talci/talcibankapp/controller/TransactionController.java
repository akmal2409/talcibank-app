package tech.talci.talcibankapp.controller;

import javafx.util.converter.TimeStringConverter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tech.talci.talcibankapp.domain.*;
import tech.talci.talcibankapp.services.*;
import tech.talci.talcibankapp.validators.TransactionValidator;
import tech.talci.talcibankapp.validators.WithdrawalValidator;

import javax.validation.Valid;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/client/{clientId}/account/{accountId}")
public class TransactionController {

    private final TransactionService transactionService;
    private final ClientService clientService;
    private final AccountService accountService;
    private final WithdrawalService withdrawalService;
    private final DepositService depositService;

    public TransactionController(TransactionService transactionService, ClientService clientService,
                                 AccountService accountService, WithdrawalService withdrawalService,
                                 DepositService depositService) {
        this.transactionService = transactionService;
        this.clientService = clientService;
        this.accountService = accountService;
        this.withdrawalService = withdrawalService;
        this.depositService = depositService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("client")
    public Client getClient(@PathVariable Long clientId){
        return clientService.findById(clientId);
    }

    @ModelAttribute("account")
    public Account getAccount(@PathVariable Long accountId){
        return accountService.findById(accountId);
    }

    @GetMapping("/transfer")
    public String getTransferForm(@PathVariable Long accountId,
                                  Model model, Account account){

        Transaction transaction = new Transaction();
        transaction.setSender(account);
        model.addAttribute("transaction", transaction);

        return "client/account/transferForm";
    }

    @PostMapping("/transfer")
    public String processTransferForm(@Valid Transaction transaction, BindingResult result,
                                      @PathVariable Long clientId, Account account){

        TransactionValidator validator = new TransactionValidator();
        transaction.setSender(account);
        Long recipientNumber = Long.valueOf(transaction.getRecipient());
        Double amount = Double.valueOf(transaction.getAmount());
        if(result.hasErrors()){

            return "client/account/transferForm";
        } else if(!validator.validate(transaction.getSender(), amount)){
            result.rejectValue("amount", "amount", "exceeds or not enough");
            return "client/account/transferForm";
        } else{
            Account sender = transaction.getSender();

            if(accountService.findByNumber(recipientNumber) == null){
                sender.setBalance(sender.getBalance() - transaction.getAmount());
                transaction.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
                sender.getTransactions().add(transaction);

                accountService.save(sender);
                transactionService.save(transaction);
            } else{
                Account recipient = accountService.findByNumber(recipientNumber);
                sender.setBalance(sender.getBalance() - transaction.getAmount());
                recipient.setBalance(recipient.getBalance() + transaction.getAmount());
                transaction.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
                sender.getTransactions().add(transaction);

                accountService.save(sender);
                accountService.save(recipient);
            }
        }

        return "redirect:/cabinet/" + clientId;
    }

    @GetMapping("/withdraw")
    public String getWithdrawalForm(Account account, Model model){

        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setAccount(account);
        model.addAttribute("withdrawal", withdrawal);

        return "client/account/withdrawalForm";
    }

    @PostMapping("/withdraw")
    public String processWithdrawalForm(@PathVariable Long accountId, @PathVariable Long clientId,
                                        @Valid Withdrawal withdrawal, Account account, BindingResult result){

        WithdrawalValidator validator = new WithdrawalValidator();
        withdrawal.setAccount(account);

        if(result.hasErrors()){
            return "client/account/withdrawalForm";
        } else if (!validator.validate(account, withdrawal.getAmount())){
            result.rejectValue("amount", "amount", "Exceeding or not enough");
            return "client/account/withdrawalForm";
        } else{
            withdrawal.setDate(Date.valueOf(LocalDate.now()));
            account.getWithdrawals().add(withdrawal);
            account.setBalance(account.getBalance() - withdrawal.getAmount());

            accountService.save(account);
            withdrawalService.save(withdrawal);

            return "redirect:/cabinet/" + clientId;
        }
    }

    @GetMapping("/deposit")
    public String getDepositForm(@PathVariable Long accountId, @PathVariable Long clientId,
                                 Model model, Account account){

        Deposit deposit = new Deposit();
        deposit.setAccount(account);
        model.addAttribute("deposit", deposit);

        return "client/account/depositForm";
    }

    @PostMapping("/deposit")
    public String processDepositForm(@PathVariable Long clientId,
                                     @Valid Deposit deposit, Account account, BindingResult result){

        deposit.setAccount(account);
        if(result.hasErrors()){
            return "client/account/depositForm";
        } else if(deposit.getAmount() < 1){

            result.rejectValue("amount", "amount", "Incorrect amount");
            return "client/account/depositForm";
        } else{
            deposit.setDate(Date.valueOf(LocalDate.now()));
            account.getDeposits().add(deposit);
            account.setBalance(account.getBalance() + deposit.getAmount());

            depositService.save(deposit);
            accountService.save(account);

            return "redirect:/cabinet/" + clientId;
        }
    }

}
