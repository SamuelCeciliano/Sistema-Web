package com.vsmanutencoes.sistemaweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vsmanutencoes.sistemaweb.service.UsersService;

@Configuration
public class AppConfig {

    @Autowired
    private UsersService usuarioService;

    @Bean
    public CommandLineRunner inicializarAdmin() {
        return args -> {
            // Garantir que o usuário admin exista
            usuarioService.garantirAdminExistente();
        };
    }
}
