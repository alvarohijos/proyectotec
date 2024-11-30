package com.proyecto.proyectotec.servicios;

import com.proyecto.proyectotec.modelo.Proyecto;
import com.proyecto.proyectotec.repositorio.ProyectoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class ProyectoServicio {

    @Autowired
    private ProyectoRepositorio proyectoRepositorio;

    // Guardar un proyecto
    public Proyecto guardar(Proyecto proyecto) {
        return proyectoRepositorio.save(proyecto);
    }

    // Obtener todos los proyectos
    public List<Proyecto> obtenerTodos() {
        return proyectoRepositorio.findAll();
    }

    // Obtener un proyecto por ID
    public Proyecto obtenerPorId(Integer id) {
        return proyectoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con ID: " + id));
    }

    // Actualizar un proyecto
    public Proyecto actualizar(Integer id, Proyecto proyectoActualizado) {
        Proyecto proyectoExistente = obtenerPorId(id);
        proyectoExistente.setNombre(proyectoActualizado.getNombre());
        proyectoExistente.setDescripcion(proyectoActualizado.getDescripcion());
        proyectoExistente.setEstado(proyectoActualizado.getEstado());
        proyectoExistente.setFechaInicio(proyectoActualizado.getFechaInicio());
        proyectoExistente.setFechaFin(proyectoActualizado.getFechaFin());
        proyectoExistente.setUbicacion(proyectoActualizado.getUbicacion());
        proyectoExistente.setOrganizacion(proyectoActualizado.getOrganizacion());
        return proyectoRepositorio.save(proyectoExistente);
    }

//    public ResponseEntity<String> eliminar(Integer id) {
//        Optional<Proyecto> proyectoExistente = proyectoRepositorio.findById(id);
//
//        if (proyectoExistente.isPresent()) {
//            try {
//                proyectoRepositorio.deleteById(id);
//                return ResponseEntity.ok("Proyecto eliminado exitosamente con ID: " + id);
//            } catch (Exception e) {
//                // Aquí, si el proyecto no se puede eliminar, es probable que sea por una restricción de la base de datos
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puede eliminar el proyecto con ID: " + id + ". Puede estar relacionado con otros datos.");
//            }
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró un proyecto con el ID: " + id + " para eliminar.");
//        }
//    }
//

    public String eliminar(Integer id) {
        Proyecto proyectoExistente = proyectoRepositorio.findById(id).orElse(null);
        if (proyectoExistente != null) {
            // Verificar si el proyecto tiene una organización asociada
            if (proyectoExistente.getOrganizacion() != null) {
                return "No se puede eliminar el proyecto con ID: " + id + ". Está asociado a una organización.";
            }
            try {
                proyectoRepositorio.deleteById(id);
                return "Proyecto eliminado exitosamente con ID: " + id;
            } catch (Exception e) {
                return "Ocurrió un error al intentar eliminar el proyecto con ID: " + id;
            }
        } else {
            return "No se encontró un proyecto con el ID: " + id + " para eliminar.";
        }
    }

    public Proyecto actualizarParcial(Integer id, Proyecto proyectoParcial) {
        Proyecto proyectoExistente = obtenerPorId(id); // Lanza excepción si no se encuentra

        if (proyectoParcial.getNombre() != null) {
            proyectoExistente.setNombre(proyectoParcial.getNombre());
        }
        if (proyectoParcial.getDescripcion() != null) {
            proyectoExistente.setDescripcion(proyectoParcial.getDescripcion());
        }
        if (proyectoParcial.getEstado() != null) {
            proyectoExistente.setEstado(proyectoParcial.getEstado());
        }
        if (proyectoParcial.getFechaInicio() != null) {
            proyectoExistente.setFechaInicio(proyectoParcial.getFechaInicio());
        }
        if (proyectoParcial.getFechaFin() != null) {
            proyectoExistente.setFechaFin(proyectoParcial.getFechaFin());
        }
        if (proyectoParcial.getUbicacion() != null) {
            proyectoExistente.setUbicacion(proyectoParcial.getUbicacion());
        }
        if (proyectoParcial.getOrganizacion() != null) {
            proyectoExistente.setOrganizacion(proyectoParcial.getOrganizacion());
        }

        return proyectoRepositorio.save(proyectoExistente);
    }

}

