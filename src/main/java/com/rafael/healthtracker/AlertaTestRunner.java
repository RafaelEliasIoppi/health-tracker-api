package com.rafael.healthtracker;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rafael.healthtracker.model.Alerta;
import com.rafael.healthtracker.model.PressaoArterial;
import com.rafael.healthtracker.model.Usuario;
import com.rafael.healthtracker.repository.AlertaRepository;
import com.rafael.healthtracker.repository.GlicemiaRepository;
import com.rafael.healthtracker.repository.PressaoArterialRepository;
import com.rafael.healthtracker.repository.SonoRepository;
import com.rafael.healthtracker.repository.UsuarioRepository;
import com.rafael.healthtracker.service.AlertaService;

@SpringBootApplication
public class AlertaTestRunner {

    public static void main(String[] args) {
        SpringApplication.run(AlertaTestRunner.class, args);
    }

    @Bean
    public CommandLineRunner run(UsuarioRepository usuarioRepo,
                                 PressaoArterialRepository pressaoRepo,
                                 GlicemiaRepository glicemiaRepo,
                                 SonoRepository sonoRepo,
                                 AlertaRepository alertaRepo,
                                 AlertaService alertaService) {
        return args -> {
            // Criar usuário
            Usuario usuario = new Usuario();
            usuario.setNome("Rafael");
            usuario = usuarioRepo.save(usuario);
            Long usuarioId = usuario.getId();

            // Inserir dados críticos
           pressaoRepo.save(new PressaoArterial(150, 95, LocalDateTime.now(), usuario));
           pressaoRepo.save(new PressaoArterial(130, 85, LocalDateTime.now().minusDays(1), usuario));    
           pressaoRepo.save(new PressaoArterial(145, 92, LocalDateTime.now().minusDays(2), usuario));

            // Executar verificação
            alertaService.verificarAlertas(usuarioId);

            // Mostrar alertas
            List<Alerta> alertas = alertaRepo.findByUsuarioId(usuarioId);
            System.out.println("🔔 Alertas gerados:");
            for (Alerta alerta : alertas) {
                System.out.println("[" + alerta.getTipo() + "] " + alerta.getMensagem());
            }
        };
    }
}
