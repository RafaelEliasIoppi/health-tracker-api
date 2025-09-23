package com.rafael.healthtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafael.healthtracker.model.Glicemia;

public interface GlicemiaRepository extends JpaRepository<Glicemia, Long> {
    // Exemplo: List<Glicemia> findByUsuarioId(Long usuarioId);
}