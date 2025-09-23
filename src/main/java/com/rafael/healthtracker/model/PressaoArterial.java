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
 * Representa um registro de pressão arterial de um usuário.
 */
@Entity
public class PressaoArterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int sistolica;     // Pressão sistólica (ex: 120)
    private int diastolica;    // Pressão diastólica (ex: 80)

    private LocalDateTime dataHora; // Data e hora do registro

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Constructors
    public PressaoArterial() {}

    public PressaoArterial(int sistolica, int diastolica, LocalDateTime dataHora, Usuario usuario) {
        this.sistolica = sistolica;
        this.diastolica = diastolica;
        this.dataHora = dataHora;
        this.usuario = usuario;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public int getSistolica() {
        return sistolica;
    }

    public void setSistolica(int sistolica) {
        this.sistolica = sistolica;
    }

    public int getDiastolica() {
        return diastolica;
    }

    public void setDiastolica(int diastolica) {
        this.diastolica = diastolica;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
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
        return "PressaoArterial{" +
                "id=" + id +
                ", sistolica=" + sistolica +
                ", diastolica=" + diastolica +
                ", dataHora=" + dataHora +
                ", usuario=" + (usuario != null ? usuario.getId() : null) +
                '}';
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PressaoArterial)) return false;
        PressaoArterial that = (PressaoArterial) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
