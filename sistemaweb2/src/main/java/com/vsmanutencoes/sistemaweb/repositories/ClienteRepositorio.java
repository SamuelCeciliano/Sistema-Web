package com.vsmanutencoes.sistemaweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vsmanutencoes.sistemaweb.models.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
}