package com.vsmanutencoes.sistemaweb.repositories;

import com.vsmanutencoes.sistemaweb.models.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepositorio extends JpaRepository<Users, Long> {
}


