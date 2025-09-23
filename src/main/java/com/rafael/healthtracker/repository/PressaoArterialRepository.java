package com.rafael.healthtracker.repository;

import com.rafael.healthtracker.model.PressaoArterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para operações CRUD com a entidade PressaoArterial.
 */
@Repository
public interface PressaoArterialRepository extends JpaRepository<PressaoArterial, Long> {
    // List<PressaoArterial> findByUsuarioId(Long usuarioId);
}