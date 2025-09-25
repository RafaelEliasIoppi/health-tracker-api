package com.rafael.healthtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rafael.healthtracker.model.Glicemia;

@Repository
public interface GlicemiaRepository extends JpaRepository<Glicemia, Long> {

    List<Glicemia> findByUsuarioId(Long usuarioId);

    @Modifying
    @Query("DELETE FROM Glicemia g WHERE g.usuario.id = :usuarioId")
    void deleteByUsuarioId(@Param("usuarioId") Long usuarioId);
}
