package tech.talci.talcibankapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tech.talci.talcibankapp.domain.Client;
import tech.talci.talcibankapp.services.ClientService;

@Slf4j
@Controller
@RequestMapping("/cabinet/{clientId}")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @ModelAttribute("client")
    public Client getClient(@PathVariable Long clientId){
        return clientService.findById(clientId);
    }

    @GetMapping
    public String getUserCabinet(Client client, Model model){

        model.addAttribute("client", client);
        log.debug("Bringing client cabinet");

        return "client/cabinet";
    }

    @GetMapping("/transactions")
    public String showTransactions(Client client, Model model){

        model.addAttribute("client", client);

        return "client/transactions";
    }

    @GetMapping("/withdrawals")
    public String getWithdrawalHistory(Client client, Model model){

        model.addAttribute("client", client);

        return "client/account/withdrawalHistory";
    }

    @GetMapping("/deposits")
    public String getDepositHistory(Client client, Model model){

        model.addAttribute("client", client);

        return "client/account/depositHistory";
    }
}
