package com.rafael.healthtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rafael.healthtracker.model.PressaoArterial;

/**
 * Repositório para operações CRUD com a entidade PressaoArterial.
 */
@Repository
public interface PressaoArterialRepository extends JpaRepository<PressaoArterial, Long> {

    List<PressaoArterial> findByUsuarioId(Long usuarioId);

    @Modifying
    @Query("DELETE FROM PressaoArterial p WHERE p.usuario.id = :usuarioId")
    void deleteByUsuarioId(@Param("usuarioId") Long usuarioId);
}
