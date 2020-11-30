package com.example.service;

import com.example.domain.Deposit;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface DepositService {

    Optional<Deposit> findByUserId(@NotNull Long userId);

    void enrichDeposit(@NotNull Deposit deposit);

    void withdrawDeposit(@NotNull Deposit deposit);

    Optional<Integer> findDepositValueByUserId(@NotNull Long userId);

}
