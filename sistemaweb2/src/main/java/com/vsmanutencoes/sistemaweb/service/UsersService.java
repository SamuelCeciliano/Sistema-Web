package com.vsmanutencoes.sistemaweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vsmanutencoes.sistemaweb.models.Users;
import com.vsmanutencoes.sistemaweb.repositories.UsersRepositorio;

@Service
public class UsersService {

    @Autowired
    private UsersRepositorio usersRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Método para listar todos os usuários
    public List<Users> listarUsuarios() {
        return usersRepositorio.findAll();
    }

    // Método para salvar um novo usuário
    public Users salvarUsuario(Users usuario) {
        // Verificar se o usuário já existe com o mesmo username
        Optional<Users> usuarioExistente = usersRepositorio.findByUsername(usuario.getUsername());
        if (usuarioExistente.isPresent()) {
            // Se o usuário já existir, não realiza a inserção
            System.out.println("O usuário com o nome '" + usuario.getUsername() + "' já existe. A inserção foi ignorada.");
            return null;
        }

        // Criptografa a senha antes de salvar
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usersRepositorio.save(usuario);
    }

    // Método para garantir que o usuário admin exista
    public void garantirAdminExistente() {
        // Verificar se o usuário admin já existe
        Optional<Users> adminExistente = usersRepositorio.findByUsername("admin");
        if (!adminExistente.isPresent()) {
            // Se o usuário admin não existir, criar e salvar
            Users admin = new Users();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("adminPassword"));  // Defina uma senha segura aqui
            admin.setRole("ADMIN");
            admin.setAtivo(true);  // O admin está ativo por padrão
            usersRepositorio.save(admin);
            System.out.println("Usuário admin criado com sucesso.");
        } else {
            System.out.println("O usuário admin já existe, nenhum cadastro foi realizado.");
        }
    }

    // Método para buscar um usuário pelo ID
    public Users buscarUsuarioPorId(Long id) {
        return usersRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
    }

    // Método para atualizar um usuário existente
    public Users atualizarUsuario(Long id, Users usuarioAtualizado) {
        Users usuario = buscarUsuarioPorId(id);
        usuario.setUsername(usuarioAtualizado.getUsername());
        usuario.setPassword(usuarioAtualizado.getPassword());
        return usersRepositorio.save(usuario);
    }

    // Inativar usuário
    public void inativarUsuario(Long id) {
        Optional<Users> userOpt = usersRepositorio.findById(id);
        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            user.setAtivo(false); // Define o status como inativo
            usersRepositorio.save(user);
        }
    }

    // Método para buscar um usuário pelo username
    public Users buscarUsuarioPorUsername(String username) {
        return usersRepositorio.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o username: " + username));
    }

    // Método opcional para buscar usuário por ID
    public Optional<Users> buscarUsuarioPorIdOptional(Long id) {
        return usersRepositorio.findById(id);
    }
}
