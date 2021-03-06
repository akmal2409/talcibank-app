package tech.talci.talcibankapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tech.talci.talcibankapp.commands.AccountCommand;
import tech.talci.talcibankapp.commands.ClientCommand;
import tech.talci.talcibankapp.domain.Client;
import tech.talci.talcibankapp.services.AccountService;
import tech.talci.talcibankapp.services.ClientService;

import javax.persistence.Column;

@Controller
@RequestMapping("/client")
public class AccountController {

    private final AccountService accountService;
    private final ClientService clientService;

    public AccountController(AccountService accountService, ClientService clientService) {
        this.accountService = accountService;
        this.clientService = clientService;
    }

    @GetMapping("/{clientId}/account/new")
    public String createNewAccount(@PathVariable String clientId, Model model){

        ClientCommand clientCommand = clientService.findCommandById(Long.valueOf(clientId));

        AccountCommand accountCommand = new AccountCommand();
        accountCommand.setClientID(Long.valueOf(clientId));
        model.addAttribute("account", accountCommand);

        return "client/account/accountForm";
    }

    @PostMapping("/account")
    public String saveOrUpdate(@ModelAttribute AccountCommand command){

        AccountCommand savedCommand = accountService.saveAccountCommand(command);

        return "redirect:/cabinet/" + savedCommand.getClientID();
    }

}
