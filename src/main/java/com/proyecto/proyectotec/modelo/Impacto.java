package com.proyecto.proyectotec.modelo;


import jakarta.persistence.*;

@Entity
@Table(name = "impacto")
public class Impacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer impactoId;

    @ManyToOne
    @JoinColumn(name = "proyecto_id", nullable = false)

    private Proyecto proyecto;

    private Integer beneficiarios;
    private Float satisfaccion;
    private String otrosIndicadores;

    // Getters y Setters de impactoprue
    public Integer getImpactoId() {
        return impactoId;
    }

    public void setImpactoId(Integer impactoId) {
        this.impactoId = impactoId;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Integer getBeneficiarios() {
        return beneficiarios;
    }

    public void setBeneficiarios(Integer beneficiarios) {
        this.beneficiarios = beneficiarios;
    }

    public Float getSatisfaccion() {
        return satisfaccion;
    }

    public void setSatisfaccion(Float satisfaccion) {
        this.satisfaccion = satisfaccion;
    }

    public String getOtrosIndicadores() {
        return otrosIndicadores;
    }

    public void setOtrosIndicadores(String otrosIndicadores) {
        this.otrosIndicadores = otrosIndicadores;
    }
}

