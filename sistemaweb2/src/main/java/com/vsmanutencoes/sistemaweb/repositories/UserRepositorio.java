package com.vsmanutencoes.sistemaweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vsmanutencoes.sistemaweb.models.User;

public interface UserRepositorio extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
}
