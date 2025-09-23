package com.rafael.healthtracker.service;

import com.rafael.healthtracker.model.*;
import com.rafael.healthtracker.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class AlertaService {

    @Autowired
    private PressaoArterialRepository pressaoRepo;

    @Autowired
    private GlicemiaRepository glicemiaRepo;

    @Autowired
    private SonoRepository sonoRepo;

    public void verificarAlertas(Long usuarioId) {
        verificarPressao(usuarioId);
        verificarGlicemia(usuarioId);
        verificarSono(usuarioId);
    }

    private void verificarPressao(Long usuarioId) {
        List<PressaoArterial> registros = pressaoRepo.findByUsuarioId(usuarioId);
        for (PressaoArterial p : registros) {
            if (p.getSistolica() >= 140 || p.getDiastolica() >= 90) {
                System.out.println("⚠️ Alerta: Pressão arterial elevada detectada em " + p.getDataHora());
            }
        }
    }

    private void verificarGlicemia(Long usuarioId) {
        List<Glicemia> registros = glicemiaRepo.findByUsuarioId(usuarioId);
        for (Glicemia g : registros) {
            if (g.getNivel() < 70 || g.getNivel() > 180) {
                System.out.println("⚠️ Alerta: Glicemia fora do intervalo saudável em " + g.getDataHora());
            }
        }
    }

    private void verificarSono(Long usuarioId) {
        List<Sono> registros = sonoRepo.findByUsuarioId(usuarioId);
        for (Sono s : registros) {
            Duration duracao = Duration.between(s.getInicio(), s.getFim());
            long horas = duracao.toHours();
            if (horas < 6 || s.getQualidade() < 5) {
                System.out.println("⚠️ Alerta: Sono insuficiente ou de baixa qualidade em " + s.getInicio());
            }
        }
    }
}