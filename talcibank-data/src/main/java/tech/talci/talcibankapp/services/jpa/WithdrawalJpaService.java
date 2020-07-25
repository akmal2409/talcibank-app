package tech.talci.talcibankapp.services.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import tech.talci.talcibankapp.domain.Withdrawal;
import tech.talci.talcibankapp.repositories.WithdrawalRepository;
import tech.talci.talcibankapp.services.WithdrawalService;

@Service
@Profile("jpa")
public class WithdrawalJpaService extends AbstractJpaService<Withdrawal, WithdrawalRepository>
        implements WithdrawalService {

    public WithdrawalJpaService(WithdrawalRepository repository) {
        super(repository);
    }
}
