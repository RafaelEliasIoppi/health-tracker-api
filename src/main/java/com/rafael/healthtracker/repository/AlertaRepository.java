package com.rafael.healthtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafael.healthtracker.model.Alerta;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    List<Alerta> findByUsuarioId(Long usuarioId);
}