package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.Set;

@Controller
public class RootController {
    private final UserService userService;

    public RootController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/root")
    public String redirect(Model model, Principal principal) {
        Set<String> roles = AuthorityUtils.authorityListToSet(userService.loadUserByUsername(principal.getName()).getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            return "redirect:/admin";
        }
        return "redirect:/user";
    }

    @GetMapping("/mylogout")
    public String logout(Model model, Principal principal) {
        return "logout";
    }
}
