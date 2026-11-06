package com.tecsup.controller;

import com.tecsup.dto.EquipoDTO;
import com.tecsup.model.Equipo;
import com.tecsup.services.EquipoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    @Autowired
    private EquipoService service;

    @GetMapping
    public ResponseEntity<List<Equipo>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @GetMapping("/{id}/detalle")
    public ResponseEntity<Map<String, Object>> detalle(@PathVariable Integer id) {
        return ResponseEntity.ok(service.detalle(id));
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Equipo>> listarPorCliente(@PathVariable Integer idCliente) {
        return ResponseEntity.ok(service.listarPorCliente(idCliente));
    }

    @PostMapping
    public ResponseEntity<Equipo> registrar(@Valid @RequestBody EquipoDTO dto) {
        return ResponseEntity.status(201).body(service.registrar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipo> actualizar(@PathVariable Integer id, @Valid @RequestBody EquipoDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
