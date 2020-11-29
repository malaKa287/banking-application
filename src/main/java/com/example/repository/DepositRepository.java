package com.example.repository;

import com.example.domain.Deposit;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Validated
@Repository
public interface DepositRepository extends CrudRepository<Deposit, Long> {

    Optional<Deposit> findByUserId(@NotNull Long userId);

    @Transactional
    @Modifying
    @Query("UPDATE Deposit d SET d.value = ?1 WHERE d.user.id = ?2")
    void updateDepositValueByUserId(@NotNull Integer value, @NotNull Long userId);
}
