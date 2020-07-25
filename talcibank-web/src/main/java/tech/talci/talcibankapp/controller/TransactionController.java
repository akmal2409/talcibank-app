package tech.talci.talcibankapp.controller;

import javafx.util.converter.TimeStringConverter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tech.talci.talcibankapp.domain.Account;
import tech.talci.talcibankapp.domain.Transaction;
import tech.talci.talcibankapp.domain.Withdrawal;
import tech.talci.talcibankapp.services.*;
import tech.talci.talcibankapp.validators.TransactionValidator;

import javax.validation.Valid;
import java.sql.Time;
import java.sql.Timestamp;
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

    @GetMapping("/transfer")
    public String getTransferForm(@PathVariable Long accountId,
                                  Model model){

        Transaction transaction = new Transaction();
        transaction.setSender(accountService.findById(accountId));
        model.addAttribute("transaction", transaction);

        return "client/account/transferForm";
    }

    @PostMapping("/transfer")
    public String processTransferForm(@Valid Transaction transaction, BindingResult result,
                                      @PathVariable Long clientId, @PathVariable Long accountId){

        TransactionValidator validator = new TransactionValidator();
        transaction.setSender(accountService.findById(accountId));
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
    public String getWithdrawalForm(@PathVariable Long accountId, Model model){

        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setAccount(accountService.findById(accountId));
        model.addAttribute("withdrawal", withdrawal);

        return "client/account/withdrawalForm";
    }

}
