package com.vsmanutencoes.sistemaweb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vsmanutencoes.sistemaweb.models.Material;

public interface MaterialRepositorio extends JpaRepository<Material, Long> {

    @Query("SELECT m FROM Material m WHERE "
         + "(:nome IS NULL OR LOWER(m.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND "
         + "(:marca IS NULL OR LOWER(m.marca) LIKE LOWER(CONCAT('%', :marca, '%'))) AND "
         + "(:modelo IS NULL OR LOWER(m.modelo) LIKE LOWER(CONCAT('%', :modelo, '%')))")
    List<Material> findByFiltros(@Param("nome") String nome,
                                 @Param("marca") String marca,
                                 @Param("modelo") String modelo);
}
