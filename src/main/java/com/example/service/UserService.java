package com.example.service;

import com.example.domain.User;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UserService {

    User findByEmail(@NotBlank @Email String email);

    User save(@NotNull User user);
}
