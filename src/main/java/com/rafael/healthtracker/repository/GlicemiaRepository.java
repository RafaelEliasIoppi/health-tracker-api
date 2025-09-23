package com.rafael.healthtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafael.healthtracker.model.Glicemia;

public interface GlicemiaRepository extends JpaRepository<Glicemia, Long> {
     List<Glicemia> findByUsuarioId(Long usuarioId);
}