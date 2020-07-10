package tech.talci.talcibankapp.bootstrap;

import tech.talci.talcibankapp.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;
import tech.talci.talcibankapp.repositories.AccountRepository;
import tech.talci.talcibankapp.repositories.CardRepository;
import tech.talci.talcibankapp.repositories.ClientRepository;
import tech.talci.talcibankapp.repositories.TransactionRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DataLoader implements CommandLineRunner {

    private AccountRepository accountRepository;
    private CardRepository cardRepository;
    private TransactionRepository transactionRepository;
    private ClientRepository clientRepository;

    @Autowired
    public DataLoader(AccountRepository accountRepository, CardRepository cardRepository, ClientRepository clientRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.cardRepository = cardRepository;
        this.clientRepository = clientRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Client akmal = new Client();
        Client gabriel = new Client();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        akmal.setFirstName("Akmal");
        akmal.setLastName("Ali");
        akmal.setAddress("Podmilscakova 55b, Ljubljna");
        akmal.setBirthDate(LocalDate.parse("28-05-1999", formatter));
        akmal.setSecretPhrase("2409");

        gabriel.setFirstName("Gabriel");
        gabriel.setLastName("Fusc");
        gabriel.setAddress("Podmilscakova 65b, Ljubljna");
        gabriel.setBirthDate(LocalDate.parse("29-05-1999", formatter));
        gabriel.setSecretPhrase("2314");

        clientRepository.save(akmal);
        clientRepository.save(gabriel);

        Account akmalsAccount1 = new Account();
        akmalsAccount1.setAccountType(AccountType.BUSINESS);
        akmalsAccount1.setBalance(2000000);
        akmalsAccount1.setName("Coffeeshop account");
        akmalsAccount1.setNumber(312321321312L);
        akmal.addAccountTo(akmalsAccount1);

        Account gabrielsAccount1 = new Account();
        gabrielsAccount1.setAccountType(AccountType.PERSONAL);
        gabrielsAccount1.setNumber(3123213123213L);
        gabrielsAccount1.setName("Savings");
        gabrielsAccount1.setBalance(900000);
        gabriel.addAccountTo(gabrielsAccount1);

        accountRepository.save(akmalsAccount1);
        accountRepository.save(gabrielsAccount1);

        Card akmalsCard = new Card();
        akmalsCard.setCardType(CardType.VISA_PLATINUM);
        akmalsCard.setCurrency(Currency.EURO);
        akmalsCard.setHoldersName("Akmal Ali");
        akmalsCard.setClient(akmal);
        akmalsCard.setIssued(LocalDate.parse("23-08-2020", formatter));
        akmalsCard.setExpires(LocalDate.parse("23-08-2023", formatter));
        akmalsCard.setBalance((double) 20909);

        Card gabrielsCard = new Card();
        gabrielsCard.setCardType(CardType.MASTERCARD_PLATINUM);
        gabrielsCard.setCurrency(Currency.EURO);
        gabrielsCard.setHoldersName("Gabbo Fusco");
        gabrielsCard.setClient(gabriel);
        gabrielsCard.setIssued(LocalDate.parse("23-08-2020", formatter));
        gabrielsCard.setExpires(LocalDate.parse("23-08-2023", formatter));
        gabrielsCard.setBalance(90890.0);

        cardRepository.save(gabrielsCard);
        cardRepository.save(akmalsCard);

        akmal.addCardTo(akmalsCard);
        gabriel.addCardTo(gabrielsCard);

        clientRepository.save(akmal);
        clientRepository.save(gabriel);

        Transaction tr1 = new Transaction();
        Transaction tr2 = new Transaction();

        tr1.setAmount(900000.0);
        tr1.setCurrency(Currency.EURO);
        tr1.setDescription("Money Transfer");
        tr1.setSender(akmalsAccount1);
        tr1.setRecipient(gabrielsAccount1);
        tr1.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        akmalsAccount1.addTransaction(tr1);

        tr2.setAmount(900031200.0);
        tr2.setCurrency(Currency.DOLLAR);
        tr2.setDescription("Money Transfer back");
        tr2.setSender(gabrielsAccount1);
        tr2.setRecipient(akmalsAccount1);
        tr2.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        gabrielsAccount1.addTransaction(tr2);

        transactionRepository.save(tr1);
        transactionRepository.save(tr2);

    }

}
