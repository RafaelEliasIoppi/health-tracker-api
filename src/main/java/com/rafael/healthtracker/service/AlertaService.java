package com.rafael.healthtracker.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rafael.healthtracker.model.Alerta;
import com.rafael.healthtracker.model.Glicemia;
import com.rafael.healthtracker.model.PressaoArterial;
import com.rafael.healthtracker.model.Sono;
import com.rafael.healthtracker.model.Usuario;
import com.rafael.healthtracker.repository.AlertaRepository;
import com.rafael.healthtracker.repository.GlicemiaRepository;
import com.rafael.healthtracker.repository.PressaoArterialRepository;
import com.rafael.healthtracker.repository.SonoRepository;
import com.rafael.healthtracker.repository.UsuarioRepository;

@Service
public class AlertaService {

    private final PressaoArterialRepository pressaoRepo;
    private final GlicemiaRepository glicemiaRepo;
    private final SonoRepository sonoRepo;
    private final AlertaRepository alertaRepo;
    private final UsuarioRepository usuarioRepo;

    public AlertaService(PressaoArterialRepository pressaoRepo,
                         GlicemiaRepository glicemiaRepo,
                         SonoRepository sonoRepo,
                         AlertaRepository alertaRepo,
                         UsuarioRepository usuarioRepo) {
        this.pressaoRepo = pressaoRepo;
        this.glicemiaRepo = glicemiaRepo;
        this.sonoRepo = sonoRepo;
        this.alertaRepo = alertaRepo;
        this.usuarioRepo = usuarioRepo;
    }

    public void verificarAlertas(Long usuarioId) {
        verificarPressao(usuarioId);
        verificarGlicemia(usuarioId);
        verificarSono(usuarioId);
    }

    private void salvarAlerta(String tipo, String mensagem, Long usuarioId) {
        Usuario usuario = usuarioRepo.findById(usuarioId).orElse(null);
        if (usuario == null) {
            System.out.println("⚠️ Usuário não encontrado: " + usuarioId);
            return;
        }

        Alerta alerta = new Alerta();
        alerta.setTipo(tipo);
        alerta.setMensagem(mensagem);
        alerta.setDataHora(LocalDateTime.now());
        alerta.setUsuario(usuario);

        alertaRepo.save(alerta);
    }

    private void verificarPressao(Long usuarioId) {
        List<PressaoArterial> registros = pressaoRepo.findByUsuarioId(usuarioId);
        for (PressaoArterial p : registros) {
            if (p.getSistolica() >= 140 || p.getDiastolica() >= 90) {
                salvarAlerta("Pressão", "Pressão arterial elevada em " + p.getDataHora(), usuarioId);
            }
        }
    }

    private void verificarGlicemia(Long usuarioId) {
        List<Glicemia> registros = glicemiaRepo.findByUsuarioId(usuarioId);
        for (Glicemia g : registros) {
            if (g.getNivel() < 70 || g.getNivel() > 180) {
                salvarAlerta("Glicemia", "Glicemia fora do intervalo saudável em " + g.getDataHora(), usuarioId);
            }
        }
    }

    private void verificarSono(Long usuarioId) {
        List<Sono> registros = sonoRepo.findByUsuarioId(usuarioId);
        for (Sono s : registros) {
            Duration duracao = Duration.between(s.getInicio(), s.getFim());
            long horas = duracao.toHours();
            if (horas < 6 || s.getQualidade() < 5) {
                salvarAlerta("Sono", "Sono insuficiente ou de baixa qualidade em " + s.getInicio(), usuarioId);
            }
        }
    }
}
