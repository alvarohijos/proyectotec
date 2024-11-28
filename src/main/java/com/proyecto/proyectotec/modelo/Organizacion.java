package com.proyecto.proyectotec.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "organizacion") // Tabla en la base de datos
public class Organizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organizacion_id") // Columna clave primaria
    private Integer organizacionId;

    @Column(name = "nombre", nullable = false, length = 100) // Nombre obligatorio
    private String nombre;

    @Column(name = "descripcion", length = 255) // Descripción opcional
    private String descripcion;

    @Column(name = "contacto", length = 50) // Contacto opcional
    private String contacto;

    @Column(name = "direccion", length = 255) // Dirección opcional
    private String direccion;

    // Constructor vacío (obligatorio para JPA)
    public Organizacion() {
    }

    // Constructor con parámetros
    public Organizacion(String nombre, String descripcion, String contacto, String direccion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.contacto = contacto;
        this.direccion = direccion;
    }

    // Getters y Setters
    public Integer getOrganizacionId() {
        return organizacionId;
    }

    public void setOrganizacionId(Integer organizacionId) {
        this.organizacionId = organizacionId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
