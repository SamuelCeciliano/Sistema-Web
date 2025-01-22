package com.vsmanutencoes.sistemaweb.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vsmanutencoes.sistemaweb.models.Equipamento;

public interface EquipamentoRepositorio extends JpaRepository<Equipamento, Long> {

    @Query("SELECT e FROM Equipamento e " +
           "WHERE (:id IS NULL OR e.id = :id) " +
           "AND (:nome IS NULL OR LOWER(e.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) " +
           "AND (:modelo IS NULL OR LOWER(e.modelo) LIKE LOWER(CONCAT('%', :modelo, '%'))) " +
           "AND (:marca IS NULL OR LOWER(e.marca) LIKE LOWER(CONCAT('%', :marca, '%')))")
    List<Equipamento> filtrarEquipamentos(
            @Param("id") Long id,
            @Param("nome") String nome,
            @Param("modelo") String modelo,
            @Param("marca") String marca);
}
