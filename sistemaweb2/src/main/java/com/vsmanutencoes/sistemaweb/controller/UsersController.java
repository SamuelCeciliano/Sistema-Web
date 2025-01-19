package com.vsmanutencoes.sistemaweb.controller;

import com.vsmanutencoes.sistemaweb.models.Users;
import com.vsmanutencoes.sistemaweb.service.UsersService;

import java.util.Optional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    // Listar todos os usuários
    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("users", usersService.listarUsuarios());
        return "users";
    }

    // Exibir formulário de novo usuário
    @GetMapping("/new")
    public String novoUserForm(Model model) {
        model.addAttribute("user", new Users());
        return "user-form";
    }

    // Salvar novo usuário ou editar usuário existente
    @PostMapping("/save")
    public String salvarUsuario(@Valid Users user) {
        usersService.salvarUsuario(user);
        return "redirect:/users";
    }

    // Exibir formulário de edição de usuário
    @GetMapping("/edit/{id}")
    public String editarUserForm(@PathVariable("id") Long id, Model model) {
        Users user = usersService.buscarUsuarioPorId(id);
        model.addAttribute("user", user);
        return "user-form";
    }

    // Inativar usuário
    @GetMapping("/delete/{id}")
    public String inativarUsuario(@PathVariable("id") Long id) {
        usersService.inativarUsuario(id);
        return "redirect:/users";
    }

    @PostMapping("/toggleStatus/{id}")
    public ResponseEntity<Void> toggleStatus(@PathVariable Long id) {
        Optional<Users> userOpt = usersService.buscarUsuarioPorIdOptional(id);
        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            user.setAtivo(!user.isAtivo()); // Alterna o status
            usersService.salvarUsuario(user); // Salva a alteração
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
