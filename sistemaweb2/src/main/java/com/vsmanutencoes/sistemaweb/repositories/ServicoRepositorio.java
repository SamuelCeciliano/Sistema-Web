package com.vsmanutencoes.sistemaweb.repositories;

import com.vsmanutencoes.sistemaweb.models.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServicoRepositorio extends JpaRepository<Servico, Long> {

    @Query("SELECT s FROM Servico s WHERE " +
           "(:id IS NULL OR s.id = :id) AND " +
           "(:nome IS NULL OR LOWER(s.nome) LIKE LOWER(CONCAT('%', :nome, '%')))")
    List<Servico> filtrarServicos(
        @Param("id") Long id,
        @Param("nome") String nome
    );
}
