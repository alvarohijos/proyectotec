package com.proyecto.proyectotec.repositorio;

import com.proyecto.proyectotec.modelo.Organizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizacionRepositorio extends JpaRepository<Organizacion, Integer> {
    // spring  genera metodos  automaticsos
    // findById
    // save
    //deleteById

}
