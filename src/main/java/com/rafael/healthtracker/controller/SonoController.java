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

import com.rafael.healthtracker.model.Sono;
import com.rafael.healthtracker.repository.SonoRepository;

/**
 * Controller REST para operações com registros de sono.
 */
@RestController
@RequestMapping("/sono")
public class SonoController {

    @Autowired
    private SonoRepository sonoRepository;

    /**
     * Lista todos os registros de sono.
     */
    @GetMapping
    public List<Sono> listarTodos() {
        return sonoRepository.findAll();
    }

    /**
     * Busca um registro específico por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Sono> buscarPorId(@PathVariable Long id) {
        Optional<Sono> registro = sonoRepository.findById(id);
        return registro.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Cadastra um novo registro de sono.
     */
    @PostMapping
    public ResponseEntity<Sono> criar(@RequestBody Sono sono) {
        Sono salvo = sonoRepository.save(sono);
        return ResponseEntity.ok(salvo);
    }

    /**
     * Atualiza um registro existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Sono> atualizar(@PathVariable Long id, @RequestBody Sono dadosAtualizados) {
        Optional<Sono> existente = sonoRepository.findById(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Sono sono = existente.get();
        sono.setInicio(dadosAtualizados.getInicio());
        sono.setFim(dadosAtualizados.getFim());
        sono.setQualidade(dadosAtualizados.getQualidade());
        sono.setUsuario(dadosAtualizados.getUsuario());

        Sono atualizado = sonoRepository.save(sono);
        return ResponseEntity.ok(atualizado);
    }

    /**
     * Remove um registro pelo ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!sonoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        sonoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}