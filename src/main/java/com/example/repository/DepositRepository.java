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

    @Transactional(readOnly = true)
    Optional<Deposit> findByUserId(@NotNull Long userId);

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "SELECT Deposit.value FROM Deposit d WHERE d.user.id =: ?1")
    //@Query("SELECT Deposit.value FROM Deposit d WHERE d.user.id = ?1")
    Optional<Integer> findDepositValueByUserId(@NotNull Long userId);

    @Transactional
    @Modifying
    @Query("UPDATE Deposit d SET d.value = ?1 WHERE d.user.id = ?2")
    void updateDepositValueByUserId(@NotNull Integer value, @NotNull Long userId);
}
