package com.vsmanutencoes.sistemaweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout-success")
    public String logoutSuccess() {
        return "logout";  // A página que vai exibir a mensagem de logout
    }
}
