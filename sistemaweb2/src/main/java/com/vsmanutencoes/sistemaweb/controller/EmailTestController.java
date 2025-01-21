package com.vsmanutencoes.sistemaweb.controller;

import com.vsmanutencoes.sistemaweb.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailTestController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/test-email")
    public String testEmail(@RequestParam String destinatario) {
        emailService.enviarEmail(destinatario, "Teste de E-mail", "Este é um e-mail de teste enviado pelo Spring Boot.");
        return "E-mail enviado com sucesso para " + destinatario;
    }
}
