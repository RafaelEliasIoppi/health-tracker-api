package com.rafael.healthtracker.controller;

import com.rafael.healthtracker.model.Alerta;
import com.rafael.healthtracker.repository.AlertaRepository;
import com.rafael.healthtracker.service.AlertaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alertas")
public class AlertaController {

    private final AlertaService alertaService;
    private final AlertaRepository alertaRepository;

    public AlertaController(AlertaService alertaService, AlertaRepository alertaRepository) {
        this.alertaService = alertaService;
        this.alertaRepository = alertaRepository;
    }

    @PostMapping("/{usuarioId}")
    public void gerarAlertas(@PathVariable Long usuarioId) {
        alertaService.verificarAlertas(usuarioId);
    }

    @GetMapping("/{usuarioId}")
    public List<Alerta> listarAlertas(@PathVariable Long usuarioId) {
        return alertaRepository.findByUsuarioId(usuarioId);
    }
}