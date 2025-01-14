package com.vsmanutencoes.sistemaweb.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.vsmanutencoes.sistemaweb.models.SolicitacaoOrcamento;

public interface SolicitacaoRepositorio extends JpaRepository<SolicitacaoOrcamento, Long> {
}
