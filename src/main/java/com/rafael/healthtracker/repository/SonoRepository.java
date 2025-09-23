package com.rafael.healthtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafael.healthtracker.model.Sono;

public interface SonoRepository extends JpaRepository<Sono, Long> {
    // Exemplo: List<Sono> findByUsuarioId(Long usuarioId);
}