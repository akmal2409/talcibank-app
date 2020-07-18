package tech.talci.talcibankapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import tech.talci.talcibankapp.commands.CardCommand;
import tech.talci.talcibankapp.services.CardService;

import javax.management.modelmbean.ModelMBeanOperationInfo;

@Controller
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("client/cards")
    public String saveNewCard(@ModelAttribute CardCommand cardCommand){

        CardCommand savedCommand = cardService.saveCardCommand(cardCommand);

        return "redirect:cabinet/" + savedCommand.getClientID();
    }

    @GetMapping("client/{clientId}/cards/new")
    public String showCardForm(@PathVariable String clientId, Model model){

        model.addAttribute("card", new CardCommand());

        return "client/card/cardForm";
    }
}
