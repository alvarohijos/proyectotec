package com.proyecto.proyectotec.servicios;

import com.proyecto.proyectotec.modelo.Organizacion;
import com.proyecto.proyectotec.repositorio.OrganizacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizacionServicio {

    @Autowired
    private OrganizacionRepositorio organizacionRepositorio;

    // Guardar una organización
    public Organizacion guardar(Organizacion organizacion) {
        return organizacionRepositorio.save(organizacion);
    }

    // Obtener todas las organizaciones
    public List<Organizacion> obtenerTodas() {
        return organizacionRepositorio.findAll();
    }



    // Obtener una organización por ID
   public Optional<Organizacion> obtenerPorId(Integer id) {
       return organizacionRepositorio.findById(id);
   }



    // Actualizar una organización
    public Organizacion actualizar(Integer id, Organizacion organizacionActualizada) {
        Organizacion organizacionExistente = organizacionRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Organización no encontrada con ID: " + id));
        organizacionExistente.setNombre(organizacionActualizada.getNombre());
        organizacionExistente.setDescripcion(organizacionActualizada.getDescripcion());
        organizacionExistente.setContacto(organizacionActualizada.getContacto());
        organizacionExistente.setDireccion(organizacionActualizada.getDireccion());
        return organizacionRepositorio.save(organizacionExistente);
    }



    // Eliminar una organización
    public void eliminar(Integer id) {
        organizacionRepositorio.deleteById(id);
    }

    public Organizacion actualizarParcial(Integer id, Organizacion organizacionParcial) {
        Organizacion organizacionExistente = organizacionRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Organización no encontrada con ID: " + id));

        if (organizacionParcial.getNombre() != null) {
            organizacionExistente.setNombre(organizacionParcial.getNombre());
        }
        if (organizacionParcial.getDescripcion() != null) {
            organizacionExistente.setDescripcion(organizacionParcial.getDescripcion());
        }
        if (organizacionParcial.getContacto() != null) {
            organizacionExistente.setContacto(organizacionParcial.getContacto());
        }
        if (organizacionParcial.getDireccion() != null) {
            organizacionExistente.setDireccion(organizacionParcial.getDireccion());
        }

        return organizacionRepositorio.save(organizacionExistente);
    }


}

