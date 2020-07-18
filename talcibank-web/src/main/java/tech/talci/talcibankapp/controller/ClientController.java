package tech.talci.talcibankapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import tech.talci.talcibankapp.services.ClientService;

@Slf4j
@Controller
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("cabinet/{clientId}")
    public String getUserCabinet(@PathVariable String clientId, Model model){

        model.addAttribute("client", clientService.findById(Long.valueOf(clientId)));
        log.debug("Bringing client cabinet");

        return "client/cabinet";
    }

    @GetMapping("cabinet/{clientId}/transactions")
    public String showTransactions(@PathVariable String clientId, Model model){

        model.addAttribute("client", clientService.findById(Long.valueOf(clientId)));

        return "client/transactions";
    }
}
