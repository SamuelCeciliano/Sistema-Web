package com.vsmanutencoes.sistemaweb.service;

import com.vsmanutencoes.sistemaweb.models.Users;
import com.vsmanutencoes.sistemaweb.repositories.UsersRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    // Método para buscar um usuário pelo ID
    public Users buscarUsuarioPorId(Long id) {
        return usersRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
    }

    // Método para salvar um novo usuário
    public Users salvarUsuario(Users usuario) {
        // Criptografa a senha antes de salvar
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usersRepositorio.save(usuario);
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

    public Users buscarUsuarioPorUsername(String username) {
        return usersRepositorio.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o username: " + username));
    }
}
