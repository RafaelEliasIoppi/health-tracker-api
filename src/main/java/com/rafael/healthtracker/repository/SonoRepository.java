package com.rafael.healthtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rafael.healthtracker.model.Sono;

@Repository
public interface SonoRepository extends JpaRepository<Sono, Long> {

    List<Sono> findByUsuarioId(Long usuarioId);

    @Modifying
    @Query("DELETE FROM Sono s WHERE s.usuario.id = :usuarioId")
    void deleteByUsuarioId(@Param("usuarioId") Long usuarioId);
}
