package com.vsmanutencoes.sistemaweb.repositories;

import com.vsmanutencoes.sistemaweb.models.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepositorio extends JpaRepository<Servico, Long> {
    
}
