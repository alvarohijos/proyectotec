package com.proyecto.proyectotec.servicios;


import com.proyecto.proyectotec.modelo.Impacto;
import com.proyecto.proyectotec.repositorio.ImpactoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImpactoServicio {

    @Autowired
    private ImpactoRepositorio impactoRepositorio;

//    public Impacto guardar(Impacto impacto) {
//        return impactoRepositorio.save(impacto);
//    }

    public Impacto guardar(Impacto impacto) {
        if (impacto.getProyecto() == null || impacto.getProyecto().getId() == null) {
            throw new RuntimeException("El proyecto asociado no puede ser nulo.");
        }
        return impactoRepositorio.save(impacto);
    }


    public List<Impacto> obtenerTodos() {
        return impactoRepositorio.findAll();
    }

    public Optional<Impacto> obtenerPorId(Integer id) {
        return impactoRepositorio.findById(id);
    }

    public Impacto actualizar(Integer id, Impacto impactoActualizado) {
        Impacto impactoExistente = impactoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Impacto no encontrado con ID: " + id));
        impactoExistente.setBeneficiarios(impactoActualizado.getBeneficiarios());
        impactoExistente.setSatisfaccion(impactoActualizado.getSatisfaccion());
        impactoExistente.setOtrosIndicadores(impactoActualizado.getOtrosIndicadores());
        return impactoRepositorio.save(impactoExistente);
    }

    public void eliminar(Integer id) {
        impactoRepositorio.deleteById(id);
    }
}
