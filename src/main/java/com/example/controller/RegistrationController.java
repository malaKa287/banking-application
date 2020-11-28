package com.example.controller;

import com.example.domain.User;
import com.example.service.UserService;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class RegistrationController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping({"/", "/login"})
    public String login(Model model, @ModelAttribute("email") String email,
                        @ModelAttribute("password") String password) {
        log.debug("Login page was opened.");
        model.addAttribute("email", email);
        model.addAttribute("password", password);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("email") @NotBlank String email,
                        @ModelAttribute("password") @NotBlank String password,
                        Model model) {
        User user = userService.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            log.debug("User with email {} was login.", email);
            return "bank";
        } else {
            log.debug("User with email {} does not exists.", email);
            model.addAttribute("error", "Email or password is incorrect.");
            return "login";
        }
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        log.debug("Registration page was opened.");
        model.addAttribute("userForm", new User());
        model.addAttribute("error");
        return "registration";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("userForm") User user, Model model) {
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            log.debug("Passwords do not match.");
            model.addAttribute("error", "password do not match");
            return "registration";
        }
        if (userService.findByEmail(user.getEmail()) != null) {
            log.debug("User with email: {} already exists.", user.getEmail());
            model.addAttribute("error", String.format("User with email: %s already exists.", user.getEmail()));
            return "registration";
        } else {
            userService.save(user);
            log.debug("User with email: {} was registered.", user.getEmail());
            return "bank";
        }
    }
}
