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
 * Representa um registro de glicemia (nível de açúcar no sangue) de um usuário.
 */
@Entity
public class Glicemia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double nivel; // nível de glicose no sangue (mg/dL)

    private LocalDateTime dataHora; // data e hora do registro

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Constructors
    public Glicemia() {}

    public Glicemia(double nivel, LocalDateTime dataHora, Usuario usuario) {
        this.nivel = nivel;
        this.dataHora = dataHora;
        this.usuario = usuario;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public double getNivel() {
        return nivel;
    }

    public void setNivel(double nivel) {
        this.nivel = nivel;
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
        return "Glicemia{" +
                "id=" + id +
                ", nivel=" + nivel +
                ", dataHora=" + dataHora +
                ", usuario=" + (usuario != null ? usuario.getId() : null) +
                '}';
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Glicemia)) return false;
        Glicemia glicemia = (Glicemia) o;
        return Objects.equals(id, glicemia.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}