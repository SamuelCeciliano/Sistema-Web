package com.vsmanutencoes.sistemaweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginPage(Model model, String error) {
        if (error != null) {
            model.addAttribute("errorMessage", "Credenciais inválidas ou usuário inativo!");
        }
        return "login";
    }
}

