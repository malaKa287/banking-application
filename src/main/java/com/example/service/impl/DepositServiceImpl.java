package com.example.service.impl;

import com.example.domain.Deposit;
import com.example.repository.DepositRepository;
import com.example.service.DepositService;
import java.util.Optional;
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
    public Optional<Deposit> findByUserId(Long userId) {
        return depositRepository.findByUserId(userId);
    }

    @Override
    public void enrichDeposit(Deposit deposit) {
        Long userId = deposit.getUser().getId();
        Optional<Deposit> userDeposit = findByUserId(userId);

        if (userDeposit.isPresent()) {
            Integer updatedDepositValue = userDeposit.get().getValue() + deposit.getValue();
            depositRepository.updateDepositValueByUserId(updatedDepositValue, userId);
            log.debug("Deposit with id: {} was enriched on {}", deposit.getId(), deposit.getValue());
        } else {
            depositRepository.save(deposit);
            log.debug("Deposit with id: {} was saved.", deposit.getId());
        }
    }

    @Override
    public void withdrawDeposit(Deposit deposit) throws IllegalArgumentException {
        Long userId = deposit.getUser().getId();
        Optional<Deposit> userDeposit = findByUserId(userId);

        if (userDeposit.isPresent()) {
            Integer currentDepositValue = userDeposit.get().getValue();
            if (currentDepositValue >= deposit.getValue()) {
                Integer updatedDepositValue = currentDepositValue - deposit.getValue();
                depositRepository.updateDepositValueByUserId(updatedDepositValue, userId);
                log.debug("Withdraw {}$ from deposit with id: {}", deposit.getValue(), deposit.getId());
            } else {
                throw new IllegalArgumentException("Not enough deposit value to withdraw.");
            }
        } else {
            log.debug("Deposit is not exists.");
            throw new IllegalArgumentException("Deposit is not exists");
        }

    }

    @Override
    public Optional<Integer> findDepositValueByUserId(@NotNull Long userId) {
        return depositRepository.findDepositValueByUserId(userId);
    }

}
