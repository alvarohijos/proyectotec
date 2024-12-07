package com.proyecto.proyectotec.controlador;


import com.proyecto.proyectotec.modelo.Impacto;
import com.proyecto.proyectotec.servicios.ImpactoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // Permite solicitudes desde tu cliente Vue
@RequestMapping("/api/impactos")
public class ImpactoControlador {

    @Autowired
    private ImpactoServicio impactoServicio;

    @PostMapping
    public ResponseEntity<Impacto> crearImpacto(@RequestBody Impacto impacto) {
        return ResponseEntity.ok(impactoServicio.guardar(impacto));
    }

    @GetMapping
    public ResponseEntity<List<Impacto>> listarImpactos() {
        return ResponseEntity.ok(impactoServicio.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Impacto> obtenerImpacto(@PathVariable Integer id) {
        return impactoServicio.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Impacto> actualizarImpacto(@PathVariable Integer id, @RequestBody Impacto impacto) {
        return ResponseEntity.ok(impactoServicio.actualizar(id, impacto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Impacto> actualizarParcialImpacto(@PathVariable Integer id, @RequestBody Impacto impacto) {
        Impacto impactoExistente = impactoServicio.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Impacto no encontrado con ID: " + id));
        if (impacto.getBeneficiarios() != null) {
            impactoExistente.setBeneficiarios(impacto.getBeneficiarios());
        }
        if (impacto.getSatisfaccion() != null) {
            impactoExistente.setSatisfaccion(impacto.getSatisfaccion());
        }
        if (impacto.getOtrosIndicadores() != null) {
            impactoExistente.setOtrosIndicadores(impacto.getOtrosIndicadores());
        }
        return ResponseEntity.ok(impactoServicio.guardar(impactoExistente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarImpacto(@PathVariable Integer id) {
        impactoServicio.eliminar(id);
        return ResponseEntity.ok("Impacto eliminado exitosamente");
    }
}

