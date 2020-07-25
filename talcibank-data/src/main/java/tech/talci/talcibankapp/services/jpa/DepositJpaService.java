package tech.talci.talcibankapp.services.jpa;

import lombok.Setter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import tech.talci.talcibankapp.domain.Deposit;
import tech.talci.talcibankapp.repositories.DepositRepository;
import tech.talci.talcibankapp.services.DepositService;

@Service
@Profile("jpa")
public class DepositJpaService extends AbstractJpaService<Deposit, DepositRepository> implements DepositService {

    public DepositJpaService(DepositRepository repository) {
        super(repository);
    }
}
