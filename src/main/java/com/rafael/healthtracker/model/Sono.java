package com.rafael.healthtracker.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Representa um registro de sono de um usuário.
 */
@Entity
public class Sono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime inicio;   // Início do sono
    private LocalDateTime fim;      // Fim do sono
    private int qualidade;          // Qualidade do sono (escala de 1 a 10)

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Constructors
    public Sono() {}

    public Sono(LocalDateTime inicio, LocalDateTime fim, int qualidade, Usuario usuario) {
        this.inicio = inicio;
        this.fim = fim;
        this.qualidade = qualidade;
        this.usuario = usuario;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public int getQualidade() {
        return qualidade;
    }

    public void setQualidade(int qualidade) {
        this.qualidade = qualidade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // toString
    @Override
    public String toString() {
        return "Sono{" +
                "id=" + id +
                ", inicio=" + inicio +
                ", fim=" + fim +
                ", qualidade=" + qualidade +
                ", usuario=" + (usuario != null ? usuario.getId() : null) +
                '}';
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sono)) return false;
        Sono sono = (Sono) o;
        return Objects.equals(id, sono.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}