package com.example.service;

import com.example.domain.Deposit;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface DepositService {

    void deposit(@NotNull Deposit deposit);
}
