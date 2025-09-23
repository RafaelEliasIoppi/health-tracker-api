package com.rafael.healthtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.healthtracker.service.AlertaService;

@RestController
@RequestMapping("/alertas")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    @GetMapping("/{usuarioId}")
    public void verificarAlertas(@PathVariable Long usuarioId) {
        alertaService.verificarAlertas(usuarioId);
    }
}
