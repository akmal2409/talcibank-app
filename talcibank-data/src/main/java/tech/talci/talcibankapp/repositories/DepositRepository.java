package tech.talci.talcibankapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.talcibankapp.domain.Deposit;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
}
