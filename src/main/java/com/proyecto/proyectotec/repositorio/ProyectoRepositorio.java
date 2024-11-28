package com.proyecto.proyectotec.repositorio;

import com.proyecto.proyectotec.modelo.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoRepositorio extends JpaRepository<Proyecto, Integer> {
}

