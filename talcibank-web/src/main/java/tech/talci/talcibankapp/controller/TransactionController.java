package tech.talci.talcibankapp.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.talci.talcibankapp.domain.Transaction;
import tech.talci.talcibankapp.services.AccountService;
import tech.talci.talcibankapp.services.ClientService;
import tech.talci.talcibankapp.services.TransactionService;

@Controller
@RequestMapping("/client")
public class TransactionController {

    TransactionService transactionService;
    ClientService clientService;
    AccountService accountService;

    public TransactionController(TransactionService transactionService,
                                 ClientService clientService, AccountService accountService) {
        this.transactionService = transactionService;
        this.clientService = clientService;
        this.accountService = accountService;
    }

    @GetMapping("/{clientId}/transfer")
    public String getTransferForm(@PathVariable Long clientId, Model model){

        model.addAttribute("client", clientService.findById(clientId));
        model.addAttribute("transaction", new Transaction());

        return "client/account/transferForm";
    }

}
