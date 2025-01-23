package com.vsmanutencoes.sistemaweb.repositories;

import com.vsmanutencoes.sistemaweb.models.Users;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepositorio extends JpaRepository<Users, Long> {
    boolean existsByUsername(String username);

    Optional<Users> findByUsername(String username);

    @Query("SELECT u FROM Users u WHERE "
            + "(:id IS NULL OR u.id = :id) AND "
            + "(:nome IS NULL OR LOWER(u.username) LIKE LOWER(CONCAT('%', :nome, '%'))) AND "
            + "(:cargo IS NULL OR LOWER(u.role) LIKE LOWER(CONCAT('%', :cargo, '%')))")
    List<Users> filtrarUsuarios(
        @Param("id") Long id,
        @Param("nome") String nome,
        @Param("cargo") String cargo
    );
}