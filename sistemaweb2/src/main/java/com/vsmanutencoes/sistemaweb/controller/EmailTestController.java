package com.vsmanutencoes.sistemaweb.controller;

import com.vsmanutencoes.sistemaweb.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailTestController {

    @Autowired
    private EmailService emailService;

    // Endpoint para testar o envio de e-mail
    @GetMapping("/test-email")
    public String testEmail(
            @RequestParam String destinatario,
            @RequestParam String assunto,
            @RequestParam String mensagem) {
        try {
            // Chama o método de envio de e-mail
            emailService.enviarEmail(destinatario, assunto, mensagem);
            return "E-mail enviado com sucesso para " + destinatario;
        } catch (MessagingException e) {
            // Caso haja erro ao enviar o e-mail
            e.printStackTrace();
            return "Erro ao enviar e-mail: " + e.getMessage();
        }
    }
}
