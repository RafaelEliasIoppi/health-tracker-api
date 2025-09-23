package com.rafael.healthtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rafael.healthtracker.model.Sono;

public interface SonoRepository extends JpaRepository<Sono, Long> {
     List<Sono> findByUsuarioId(Long usuarioId);
}