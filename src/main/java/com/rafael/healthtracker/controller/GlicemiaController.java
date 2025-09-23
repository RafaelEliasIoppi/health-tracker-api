package com.rafael.healthtracker.controller;



import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rafael.healthtracker.model.Glicemia;
import com.rafael.healthtracker.repository.GlicemiaRepository;

/**
 * Controller REST para operações com registros de glicemia.
 */
@RestController
@RequestMapping("/glicemia")
public class GlicemiaController {

    @Autowired
    private GlicemiaRepository glicemiaRepository;

    /**
     * Lista todos os registros de glicemia.
     */
    @GetMapping
    public List<Glicemia> listarTodos() {
        return glicemiaRepository.findAll();
    }

    /**
     * Busca um registro específico por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Glicemia> buscarPorId(@PathVariable Long id) {
        Optional<Glicemia> registro = glicemiaRepository.findById(id);
        return registro.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Cadastra um novo registro de glicemia.
     */
    @PostMapping
    public ResponseEntity<Glicemia> criar(@RequestBody Glicemia glicemia) {
        Glicemia salvo = glicemiaRepository.save(glicemia);
        return ResponseEntity.ok(salvo);
    }

    /**
     * Atualiza um registro existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Glicemia> atualizar(@PathVariable Long id, @RequestBody Glicemia dadosAtualizados) {
        Optional<Glicemia> existente = glicemiaRepository.findById(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Glicemia glicemia = existente.get();
        glicemia.setNivel(dadosAtualizados.getNivel());
        glicemia.setDataHora(dadosAtualizados.getDataHora());
        glicemia.setUsuario(dadosAtualizados.getUsuario());

        Glicemia atualizado = glicemiaRepository.save(glicemia);
        return ResponseEntity.ok(atualizado);
    }

    /**
     * Remove um registro pelo ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!glicemiaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        glicemiaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}