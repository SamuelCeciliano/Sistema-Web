package com.vsmanutencoes.sistemaweb.repositories;

import com.vsmanutencoes.sistemaweb.models.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrcamentoRepositorio extends JpaRepository<Orcamento, Long> {
}
