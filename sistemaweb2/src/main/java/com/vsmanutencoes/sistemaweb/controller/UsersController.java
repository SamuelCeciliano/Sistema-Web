package com.vsmanutencoes.sistemaweb.controller;

import com.vsmanutencoes.sistemaweb.models.Users;
import com.vsmanutencoes.sistemaweb.repositories.UsersRepositorio;
import com.vsmanutencoes.sistemaweb.service.UsersService;

import java.security.Principal;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersRepositorio usersRepositorio;

    // Listar todos os usuários
    @GetMapping
    public String listarUsuarios(
    @RequestParam(required = false) Long id,
    @RequestParam(required = false) String nome,
    @RequestParam(required = false) String cargo,
    Model model, Principal principal
    ) {
    String username = principal.getName();
    model.addAttribute("username", username);

    // Caso todos os filtros sejam nulos, traga todos os usuários
    if (id == null && nome == null && cargo == null) {
        model.addAttribute("users", usersService.listarUsuarios());
    } else {
        model.addAttribute("users", usersService.filtrarUsuarios(id, nome, cargo));
    }
    return "users";
}

    // Exibir formulário de novo usuário
    @GetMapping("/new")
    public String novoUserForm(Model model, Principal principal
    ) {
        String username = principal.getName();
        model.addAttribute("username", username);
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
    public String editarUserForm(@PathVariable("id") Long id, Model model, Principal principal
    ) {
        String username = principal.getName();
        model.addAttribute("username", username);
        Users user = usersService.buscarUsuarioPorId(id);
        model.addAttribute("user", user);
        return "user-form";
    }

    // Inativar usuário
    @GetMapping("/delete/{id}")
    public String inativarUsuario(@PathVariable("id") Long id, Model model, Principal principal
    ) {
        String username = principal.getName();
        model.addAttribute("username", username);
        usersService.inativarUsuario(id);
        return "redirect:/users";
    }

        @PostMapping("/toggleStatus/{id}")
        public ResponseEntity<Void> toggleStatus(@PathVariable Long id) {
            Optional<Users> userOpt = usersRepositorio.findById(id);
            if (userOpt.isPresent()) {
                Users user = userOpt.get();
                user.setAtivo(!user.isAtivo()); // Alterna o status
                usersRepositorio.save(user); // Salva a alteração
                System.out.println("Cliente atualizado: " + user);
                return ResponseEntity.ok().build();
            }
            System.out.println("Cliente não encontrado para o ID: " + id); // Log
        return ResponseEntity.notFound().build();
        }

}