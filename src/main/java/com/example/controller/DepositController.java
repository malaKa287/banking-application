package com.example.controller;


import com.example.domain.AuthenticatedUser;
import com.example.domain.Deposit;
import com.example.service.DepositService;
import com.example.service.operation.OperationType;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping(path = "/deposit")
public class DepositController {

    private final DepositService depositService;

    private final AuthenticatedUser authenticatedUser;

    @GetMapping
    public String depositPage(Model model) {
        model.addAttribute("deposit", new Deposit());
        return "bank";
    }

    //@GetMapping("/{id}")
    @GetMapping("/current")
    public String findDepositByUserId(Model model) {
        System.out.println(authenticatedUser.getUser().getId());

        Optional<Integer> depositValue = depositService.findDepositValueByUserId(authenticatedUser.getUser().getId());
        //String message = depositValue.isPresent() ? ""
        depositValue.map(integer -> model.addAttribute("currentDeposit", String.format("Current deposit value is: %s$", integer)))
                .orElseGet(() -> model.addAttribute("currentDeposit", "No deposit for the user"));
        return "bank";
    }

    @PostMapping
    public String updateDeposit(@ModelAttribute("deposit") Deposit deposit, Model model,
                                @RequestParam("operationType") OperationType operationType) {
        log.debug("Request to deposit money.");
        //TODO add validator
        if (deposit.getValue() == null) {
            model.addAttribute("error", "Invalid value");
            return "bank";
        } else if (deposit.getValue() <= 0) {
            model.addAttribute("error", "Invalid value");
            return "bank";
        }
        deposit.setUser(authenticatedUser.getUser());

        switch (operationType) {
            case ENRICH:
                depositService.enrichDeposit(deposit);
                model.addAttribute("message", String.format("Deposit was increased by:%s$", deposit.getValue()));
                break;
            case WITHDRAW:
                try {
                    depositService.withdrawDeposit(deposit);
                    model.addAttribute("message", String.format("Withdraw %s$ from the deposit.", deposit.getValue()));
                } catch (IllegalArgumentException e) {
                    model.addAttribute("error", "Not enough money on deposit or deposit is not exists.");
                }
                break;
        }
        return "bank";
    }

}
