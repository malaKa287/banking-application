package com.example.controller;


import com.example.domain.AuthenticatedUser;
import com.example.domain.Deposit;
import com.example.service.DepositService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping(path = "/deposit")
public class DepositController {

    private final DepositService depositService;

    private final AuthenticatedUser authenticatedUser;

    @GetMapping
    public String deposit(Model model) {
        model.addAttribute("deposit", new Deposit());
        return "bank";
    }

    @PostMapping
    public void deposit(@ModelAttribute("deposit") Deposit deposit) {
        log.debug("Request to deposit money.");
        deposit.setUser(authenticatedUser.getUser());
        depositService.deposit(deposit);
    }
}
