package com.rafael.healthtracker;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
            usuario.setNome("Rafael de Oliveira");
            usuario.setEmail("rafael@example.com");
            usuario = usuarioRepo.save(usuario);
            Long usuarioId = usuario.getId();

         pressaoRepo.save(new PressaoArterial(150, 95, LocalDateTime.now(), usuario));
         pressaoRepo.save(new PressaoArterial(145, 92, LocalDateTime.now().minusDays(1), usuario));
         pressaoRepo.save(new PressaoArterial(160, 100, LocalDateTime.now().minusDays(2), usuario));    

            // Inserir dados críticos
           glicemiaRepo.save(new Glicemia(250, LocalDateTime.now(), usuario));
           glicemiaRepo.save(new Glicemia(180, LocalDateTime.now().minusDays(1), usuario));
           glicemiaRepo.save(new Glicemia(220, LocalDateTime.now().minusDays(2), usuario));

           sonoRepo.save(new Sono(LocalDateTime.now().minusDays(1).withHour(23).withMinute(0), LocalDateTime.now().minusDays(1).withHour(7).withMinute(0), 4, usuario));
           sonoRepo.save(new Sono(LocalDateTime.now().minusDays(2).withHour(22).withMinute(30), LocalDateTime.now().minusDays(2).withHour(7).withMinute(30), 5, usuario));
           sonoRepo.save(new Sono(LocalDateTime.now().minusDays(3).withHour(0).withMinute(0), LocalDateTime.now().minusDays(3).withHour(3).withMinute(0), 3, usuario));

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
