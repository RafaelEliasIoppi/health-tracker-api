package com.rafael.healthtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafael.healthtracker.model.Sono;
import java.util.List;

public interface SonoRepository extends JpaRepository<Sono, Long> {
     List<Sono> findByUsuarioId(Long usuarioId);
}