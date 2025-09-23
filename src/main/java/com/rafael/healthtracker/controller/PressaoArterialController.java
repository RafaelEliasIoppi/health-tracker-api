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

import com.rafael.healthtracker.model.PressaoArterial;
import com.rafael.healthtracker.repository.PressaoArterialRepository;

/**
 * Controller REST para operações com registros de pressão arterial.
 */
@RestController
@RequestMapping("/pressao")
public class PressaoArterialController {

    @Autowired
    private PressaoArterialRepository pressaoRepository;

    /**
     * Lista todos os registros de pressão arterial.
     */
    @GetMapping
    public List<PressaoArterial> listarTodos() {
        return pressaoRepository.findAll();
    }

    /**
     * Busca um registro específico por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PressaoArterial> buscarPorId(@PathVariable Long id) {
        Optional<PressaoArterial> registro = pressaoRepository.findById(id);
        return registro.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Cadastra um novo registro de pressão arterial.
     */
    @PostMapping
    public ResponseEntity<PressaoArterial> criar(@RequestBody PressaoArterial pressao) {
        PressaoArterial salvo = pressaoRepository.save(pressao);
        return ResponseEntity.ok(salvo);
    }

    /**
     * Atualiza um registro existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PressaoArterial> atualizar(@PathVariable Long id, @RequestBody PressaoArterial dadosAtualizados) {
        Optional<PressaoArterial> existente = pressaoRepository.findById(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PressaoArterial pressao = existente.get();
        pressao.setSistolica(dadosAtualizados.getSistolica());
        pressao.setDiastolica(dadosAtualizados.getDiastolica());
        pressao.setDataHora(dadosAtualizados.getDataHora());
        pressao.setUsuario(dadosAtualizados.getUsuario());

        PressaoArterial atualizado = pressaoRepository.save(pressao);
        return ResponseEntity.ok(atualizado);
    }

    /**
     * Remove um registro pelo ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!pressaoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        pressaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}