package com.rafael.healthtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafael.healthtracker.model.Usuario;

/**
 * Repositório para operações CRUD com a entidade Usuario.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Exemplo de método personalizado:
    // Optional<Usuario> findByEmail(String email);
}