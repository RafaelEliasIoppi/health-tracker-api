package com.rafael.healthtracker;

import com.rafael.healthtracker.model.*;
import com.rafael.healthtracker.repository.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

public class AlertaServiceTest {

    @Test
    public void deveGerarAlertasDePressaoElevada() {
        PressaoArterialRepository pressaoRepo = mock(PressaoArterialRepository.class);
        GlicemiaRepository glicemiaRepo = mock(GlicemiaRepository.class);
        SonoRepository sonoRepo = mock(SonoRepository.class);

        AlertaService alertaService = new AlertaService();
        alertaService.pressaoRepo = pressaoRepo;
        alertaService.glicemiaRepo = glicemiaRepo;
        alertaService.sonoRepo = sonoRepo;

        PressaoArterial pressaoAlta = new PressaoArterial();
        pressaoAlta.setSistolica(150);
        pressaoAlta.setDiastolica(95);
        pressaoAlta.setDataHora(LocalDateTime.now());

        when(pressaoRepo.findByUsuarioId(1L)).thenReturn(List.of(pressaoAlta));

        alertaService.verificarAlertas(1L);

        verify(pressaoRepo, times(1)).findByUsuarioId(1L);
    }
}