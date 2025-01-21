package com.vsmanutencoes.sistemaweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public String enviarEmail(String destinatario, String assunto, String mensagem) {
        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(destinatario);
            email.setSubject(assunto);
            email.setText(mensagem);
            email.setFrom("albasques@gmail.com");

            mailSender.send(email);
            return "E-mail enviado com sucesso para " + destinatario;
        } catch (MailException e) {
            return "Erro ao enviar e-mail: " + e.getMessage();
        }
    }
}
