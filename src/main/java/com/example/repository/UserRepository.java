package com.example.repository;

import com.example.domain.User;
import javax.validation.constraints.NotBlank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Repository
@Validated
public interface UserRepository extends CrudRepository<User, Long> {

    @Transactional(readOnly = true)
    User findByEmail(@NotBlank String email);
}