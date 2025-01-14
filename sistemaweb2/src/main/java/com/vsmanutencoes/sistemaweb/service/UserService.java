package com.vsmanutencoes.sistemaweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vsmanutencoes.sistemaweb.models.User;
import com.vsmanutencoes.sistemaweb.repositories.UserRepositorio;

@Service
public class UserService {

    @Autowired
    private UserRepositorio userRepositorio;

    public User loginUser(String username, String password) {
        return userRepositorio.findByUsernameAndPassword(username, password);
    }
}


