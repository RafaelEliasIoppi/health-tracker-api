

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rafael.healthtracker.model.PressaoArterial;
import com.rafael.healthtracker.repository.GlicemiaRepository;
import com.rafael.healthtracker.repository.PressaoArterialRepository;
import com.rafael.healthtracker.repository.SonoRepository;
import com.rafael.healthtracker.service.AlertaService;


public class AlertaServiceTest {

    @Test
    public void deveGerarAlertasDePressaoElevada() {
        // Mock dos repositórios
        PressaoArterialRepository pressaoRepo = mock(PressaoArterialRepository.class);
        GlicemiaRepository glicemiaRepo = mock(GlicemiaRepository.class);
        SonoRepository sonoRepo = mock(SonoRepository.class);

        // Instância do serviço com injeção via construtor
        AlertaService alertaService = new AlertaService(pressaoRepo, glicemiaRepo, sonoRepo, null, null);

        // Simulação de dado crítico
        PressaoArterial pressaoAlta = new PressaoArterial();
        pressaoAlta.setSistolica(150);
        pressaoAlta.setDiastolica(95);
        pressaoAlta.setDataHora(LocalDateTime.now());

        // Configuração do mock
        when(pressaoRepo.findByUsuarioId(1L)).thenReturn(List.of(pressaoAlta));

        // Execução do método
        alertaService.verificarAlertas(1L);

        // Verificação
        verify(pressaoRepo, times(1)).findByUsuarioId(1L);
    }
}
