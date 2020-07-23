package tech.talci.talcibankapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import tech.talci.talcibankapp.commands.CardCommand;
import tech.talci.talcibankapp.services.CardService;

import javax.management.modelmbean.ModelMBeanOperationInfo;

@Slf4j
@Controller
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("client/card")
    public String saveNewCard(@ModelAttribute CardCommand cardCommand){

        CardCommand savedCommand = cardService.saveCardCommand(cardCommand);

        log.debug("Saved new card id :" + savedCommand.getId());

        return "redirect:/cabinet/" + savedCommand.getClientID();
    }

    @GetMapping("client/{clientId}/cards/new")
    public String showCardForm(@PathVariable String clientId, Model model){
        CardCommand cardCommand = new CardCommand();
        cardCommand.setClientID(Long.valueOf(clientId));

        model.addAttribute("card", cardCommand);

        return "client/card/cardForm";
    }

    @GetMapping("client/{clientId}/card/{cardId}/delete")
    public String deleteCard(@PathVariable Long clientId,
                             @PathVariable Long cardId){
        cardService.deleteById(cardId);

        return "redirect:/cabinet/" + clientId;
    }
}
