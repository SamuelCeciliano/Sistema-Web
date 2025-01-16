package com.vsmanutencoes.sistemaweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("pageTitle", "Página Inicial - Sistema de Solicitações");
        return "home";
    }
    
    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");
        // Substitua por um método que pega o usuário logado
        modelAndView.addObject("username", "admin");
        return modelAndView;
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @GetMapping("/consultas")
    public String consultas() {
        return "consultas"; 
    }
}