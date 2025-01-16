package com.vsmanutencoes.sistemaweb;

import com.vsmanutencoes.sistemaweb.models.Users;
import com.vsmanutencoes.sistemaweb.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemawebApplication implements CommandLineRunner {

    @Autowired
    private UsersService usersService;

    public static void main(String[] args) {
        SpringApplication.run(SistemawebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Criar usuário inicial
        Users user = new Users();
        user.setUsername("admin");
        user.setPassword("admin"); // Será criptografada no método salvarUsuario
        user.setRole("ADMIN");
        user.setAtivo(true);

        usersService.salvarUsuario(user);
        System.out.println("Usuário 'admin' criado com sucesso!");
    }
}
