package com.example.service.impl;

import com.example.domain.Deposit;
import com.example.repository.DepositRepository;
import com.example.service.DepositService;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {

    private final DepositRepository depositRepository;

    @Override
    public void save(@NotNull Deposit deposit) {
        depositRepository.save(deposit);
        log.debug("Deposit with id: {} was saved.", deposit.getId());
    }
}
