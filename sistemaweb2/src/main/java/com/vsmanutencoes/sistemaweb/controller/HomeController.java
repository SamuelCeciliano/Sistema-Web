package com.vsmanutencoes.sistemaweb.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    
    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        // Pega o nome do usuário logado
        String username = principal.getName();
        model.addAttribute("username", username);
        return "home";  // Retorna a página onde você exibe o nome
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    @GetMapping("/consultas")
    public String consultas(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("username", username);
        return "consultas"; 
    }
}