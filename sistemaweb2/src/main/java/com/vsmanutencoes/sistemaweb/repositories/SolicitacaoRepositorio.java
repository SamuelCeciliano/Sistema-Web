package com.vsmanutencoes.sistemaweb.repositories;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vsmanutencoes.sistemaweb.models.SolicitacaoOrcamento;

public interface SolicitacaoRepositorio extends JpaRepository<SolicitacaoOrcamento, Long> {

    @Query("SELECT s FROM SolicitacaoOrcamento s " +
            "WHERE (:nome IS NULL OR LOWER(s.cliente.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) " +
            "AND (:empresa IS NULL OR LOWER(s.cliente.empresa) LIKE LOWER(CONCAT('%', :empresa, '%'))) " +
            "AND (:cnpj IS NULL OR LOWER(s.cliente.cnpjCpf) LIKE LOWER(CONCAT('%', :cnpj, '%'))) " +
            "AND (:equipamento IS NULL OR EXISTS (SELECT e FROM s.equipamentos e WHERE LOWER(e.nome) LIKE LOWER(CONCAT('%', :equipamento, '%')))) " +
            "AND (:data IS NULL OR s.data = :data)")
    List<SolicitacaoOrcamento> filtrarSolicitacoes(@Param("nome") String nome,
                                                   @Param("empresa") String empresa,
                                                   @Param("cnpj") String cnpj,
                                                   @Param("equipamento") String equipamento,
                                                   @Param("data") LocalDate data);
}