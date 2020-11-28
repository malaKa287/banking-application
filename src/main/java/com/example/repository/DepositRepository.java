package com.example.repository;

import com.example.domain.Deposit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends CrudRepository<Deposit, Long> {

    //void deposit(Deposit deposit);
}
