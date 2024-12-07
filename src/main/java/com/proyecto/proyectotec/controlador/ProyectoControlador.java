package com.proyecto.proyectotec.controlador;

import com.proyecto.proyectotec.modelo.Proyecto;
import com.proyecto.proyectotec.servicios.ProyectoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // Permite solicitudes desde tu cliente Vue
@RequestMapping("/api/proyectos")
public class ProyectoControlador {

    @Autowired
    private ProyectoServicio proyectoServicio;

    // Crear un proyecto
    @PostMapping
    public ResponseEntity<Proyecto> crearProyecto(@RequestBody Proyecto proyecto) {
        return ResponseEntity.ok(proyectoServicio.guardar(proyecto));
    }

    // Obtener todos los proyectos
    @GetMapping
    public ResponseEntity<List<Proyecto>> listarProyectos() {
        return ResponseEntity.ok(proyectoServicio.obtenerTodos());
    }

    // Obtener un proyecto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> obtenerProyecto(@PathVariable Integer id) {
        return ResponseEntity.ok(proyectoServicio.obtenerPorId(id));
    }

    // Actualizar un proyecto
    @PutMapping("/{id}")
    public ResponseEntity<Proyecto> actualizarProyecto(@PathVariable Integer id, @RequestBody Proyecto proyecto) {
        return ResponseEntity.ok(proyectoServicio.actualizar(id, proyecto));
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> eliminarProyecto(@PathVariable Integer id) {
//        return proyectoServicio.eliminar(id);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProyecto(@PathVariable Integer id) {
        try {
            proyectoServicio.eliminar(id);
            return ResponseEntity.ok("Proyecto eliminado exitosamente con ID: " + id);
        } catch (IllegalArgumentException e) {
            // Proyecto no encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException e) {
            // Restricción violada (relación con organización)
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            // Otros errores inesperados
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al intentar eliminar el proyecto.");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Proyecto> actualizarParcialProyecto(@PathVariable Integer id, @RequestBody Proyecto proyectoParcial) {
        try {
            Proyecto proyectoActualizado = proyectoServicio.actualizarParcial(id, proyectoParcial);
            return ResponseEntity.ok(proyectoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}

