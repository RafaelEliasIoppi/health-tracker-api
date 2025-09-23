package com.rafael.healthtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafael.healthtracker.model.PressaoArterial;

/**
 * Repositório para operações CRUD com a entidade PressaoArterial.
 */
@Repository
public interface PressaoArterialRepository extends JpaRepository<PressaoArterial, Long> {
     List<PressaoArterial> findByUsuarioId(Long usuarioId);
}