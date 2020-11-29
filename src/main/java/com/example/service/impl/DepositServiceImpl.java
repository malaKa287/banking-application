package com.example.service.impl;

import com.example.domain.Deposit;
import com.example.repository.DepositRepository;
import com.example.service.DepositService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {

    private final DepositRepository depositRepository;

    @Override
    public void deposit(Deposit deposit) {
        Long userId = deposit.getUser().getId();
        Optional<Deposit> userDeposit = depositRepository.findByUserId(userId);
        if (userDeposit.isPresent()) {
            Integer updatedDepositValue = userDeposit.get().getValue() + deposit.getValue();

            depositRepository.updateDepositValueByUserId(updatedDepositValue, userId);
            log.debug("New value: {} for the deposit id: {}.", updatedDepositValue, deposit.getId());
        } else {
            depositRepository.save(deposit);
            log.debug("Deposit with id: {} was saved.", deposit.getId());
        }
    }

}
