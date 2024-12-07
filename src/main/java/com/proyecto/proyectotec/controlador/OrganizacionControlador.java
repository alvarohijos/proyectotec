package com.proyecto.proyectotec.controlador;

import com.proyecto.proyectotec.modelo.Organizacion;
import com.proyecto.proyectotec.servicios.OrganizacionServicio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;




@RestController
@CrossOrigin(origins = "http://localhost:5173") // Permite solicitudes desde tu cliente Vue
@RequestMapping("/api/organizaciones")
public class OrganizacionControlador {

    @Autowired
    private OrganizacionServicio organizacionServicio;

    // Crear una organización
    @PostMapping
    public ResponseEntity<Organizacion> crearOrganizacion(@RequestBody Organizacion organizacion) {
        return ResponseEntity.ok(organizacionServicio.guardar(organizacion));
    }

    // Obtener todas las organizaciones
    @GetMapping
    public ResponseEntity<List<Organizacion>> listarOrganizaciones() {
        return ResponseEntity.ok(organizacionServicio.obtenerTodas());
    }

    // Obtener una organización por ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Organizacion> obtenerOrganizacion(@PathVariable Integer id) {
//        return organizacionServicio.obtenerPorId(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Organizacion> obtenerOrganizacionPorId(@PathVariable Integer id) {
        Optional<Organizacion> organizacionOpt = organizacionServicio.obtenerPorId(id);
        if (organizacionOpt.isPresent()) {
            return ResponseEntity.ok(organizacionOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    // Actualizar una organización
    @PutMapping("/{id}")
    public ResponseEntity<Organizacion> actualizarOrganizacion(@PathVariable Integer id, @RequestBody Organizacion organizacion) {
        return ResponseEntity.ok(organizacionServicio.actualizar(id, organizacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOrganizacion(@PathVariable Integer id) {
        Optional<Organizacion> organizacionOpt = organizacionServicio.obtenerPorId(id);
        if (organizacionOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encuentra el registro para eliminar");
        }

        // Verificar relaciones dependientes (simulado)
        boolean tieneRelaciones = false; // Cambia esto según tu lógica real
        if (tieneRelaciones) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Para eliminarla debe eliminar las tablas relacionadas con esta");
        }

        // Si no hay relaciones, elimina la organización
        organizacionServicio.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Registro eliminado exitosamente");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Organizacion> patchOrganizacion(
            @PathVariable Integer id,
            @RequestBody Organizacion organizacionParcial) {
        try {
            Organizacion organizacionActualizada = organizacionServicio.actualizarParcial(id, organizacionParcial);
            return ResponseEntity.ok(organizacionActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }



}
