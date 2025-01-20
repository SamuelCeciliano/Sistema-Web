package com.vsmanutencoes.sistemaweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmail(String destinatario, String assunto, String mensagem) throws MessagingException {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo(destinatario);
            helper.setSubject(assunto);
            helper.setText(mensagem, true); // True para indicar que é HTML

            System.out.println("Tentando enviar e-mail para: " + destinatario);
            mailSender.send(mimeMessage);
            System.out.println("E-mail enviado com sucesso!");
        } catch (MessagingException e) {
            System.err.println("Erro ao enviar e-mail: " + e.getMessage());
            throw new MessagingException("Erro ao enviar e-mail", e);
        }
    }
}
