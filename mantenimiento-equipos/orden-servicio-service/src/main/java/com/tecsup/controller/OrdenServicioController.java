package com.tecsup.controller;

import com.tecsup.dto.EstadoDTO;
import com.tecsup.dto.OrdenServicioDTO;
import com.tecsup.model.EstadoOrden;
import com.tecsup.model.OrdenServicio;
import com.tecsup.services.OrdenServicioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenServicioController {

    @Autowired
    private OrdenServicioService service;

    @GetMapping
    public ResponseEntity<List<OrdenServicio>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenServicio> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @GetMapping("/{id}/detalle")
    public ResponseEntity<Map<String, Object>> detalle(@PathVariable Integer id) {
        return ResponseEntity.ok(service.detalle(id));
    }

    @GetMapping("/equipo/{idEquipo}")
    public ResponseEntity<List<OrdenServicio>> listarPorEquipo(@PathVariable Integer idEquipo) {
        return ResponseEntity.ok(service.listarPorEquipo(idEquipo));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<OrdenServicio>> listarPorEstado(@PathVariable EstadoOrden estado) {
        return ResponseEntity.ok(service.listarPorEstado(estado));
    }

    @PostMapping
    public ResponseEntity<OrdenServicio> registrar(@Valid @RequestBody OrdenServicioDTO dto) {
        return ResponseEntity.status(201).body(service.registrar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenServicio> actualizar(@PathVariable Integer id,
                                                    @Valid @RequestBody OrdenServicioDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<OrdenServicio> cambiarEstado(@PathVariable Integer id,
                                                       @Valid @RequestBody EstadoDTO dto) {
        return ResponseEntity.ok(service.cambiarEstado(id, dto.getEstado()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
