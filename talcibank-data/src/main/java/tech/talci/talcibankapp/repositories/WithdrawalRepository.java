package tech.talci.talcibankapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.talcibankapp.domain.Withdrawal;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {
}
