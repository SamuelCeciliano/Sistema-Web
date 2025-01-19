package com.vsmanutencoes.sistemaweb.service;

import com.vsmanutencoes.sistemaweb.models.Users;
import com.vsmanutencoes.sistemaweb.repositories.UsersRepositorio;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UsersRepositorio usersRepositorio;
    private static final Logger logger = Logger.getLogger(UserDetailsServiceImpl.class.getName());

    public UserDetailsServiceImpl(UsersRepositorio usersRepositorio) {
        this.usersRepositorio = usersRepositorio;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info(String.format("Tentando carregar usuário: %s", username));
        
        Users user = usersRepositorio.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
        
        if (!user.isAtivo()) {
            throw new RuntimeException("Usuário inativo.");
        }

        logger.info(String.format("Usuário encontrado: %s", user.getUsername()));

        
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) 
                .roles(user.getRole())
                .build();
    }
}
