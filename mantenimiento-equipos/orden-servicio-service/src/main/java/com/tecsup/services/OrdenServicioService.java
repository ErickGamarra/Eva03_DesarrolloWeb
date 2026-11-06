package com.tecsup.services;

import com.tecsup.client.EquipoClient;
import com.tecsup.dto.OrdenServicioDTO;
import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.EstadoOrden;
import com.tecsup.model.OrdenServicio;
import com.tecsup.repository.OrdenServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrdenServicioService {

    @Autowired
    private OrdenServicioRepository repo;

    @Autowired
    private EquipoClient equipoClient;

    public List<OrdenServicio> listar() {
        return repo.findAll();
    }

    public List<OrdenServicio> listarPorEquipo(Integer idEquipo) {
        return repo.findByIdEquipo(idEquipo);
    }

    public List<OrdenServicio> listarPorEstado(EstadoOrden estado) {
        return repo.findByEstado(estado);
    }

    public OrdenServicio obtener(Integer id) {
        return repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Orden de servicio no encontrada con id: " + id));
    }

    public Map<String, Object> detalle(Integer id) {
        OrdenServicio o = obtener(id);
        Map<String, Object> datos = new LinkedHashMap<>();
        datos.put("orden", o);
        datos.putAll(equipoClient.detalle(o.getIdEquipo()));
        return datos;
    }

    public OrdenServicio registrar(OrdenServicioDTO dto) {
        equipoClient.verificar(dto.getIdEquipo());
        OrdenServicio o = new OrdenServicio();
        copiar(dto, o);
        o.setEstado(EstadoOrden.RECIBIDO);
        return repo.save(o);
    }

    public OrdenServicio actualizar(Integer id, OrdenServicioDTO dto) {
        OrdenServicio o = obtener(id);
        if (o.getEstado() == EstadoOrden.ENTREGADO) {
            throw new IllegalArgumentException("La orden ya fue entregada y no se puede modificar");
        }
        equipoClient.verificar(dto.getIdEquipo());
        copiar(dto, o);
        return repo.save(o);
    }

    public OrdenServicio cambiarEstado(Integer id, EstadoOrden nuevo) {
        OrdenServicio o = obtener(id);
        EstadoOrden actual = o.getEstado();
        if (actual == EstadoOrden.ENTREGADO) {
            throw new IllegalArgumentException("La orden ya fue entregada, no se puede cambiar su estado");
        }
        if (nuevo.ordinal() != actual.ordinal() + 1) {
            EstadoOrden siguiente = EstadoOrden.values()[actual.ordinal() + 1];
            throw new IllegalArgumentException("No se puede pasar de " + actual + " a " + nuevo
                    + ". El siguiente estado debe ser " + siguiente);
        }
        o.setEstado(nuevo);
        return repo.save(o);
    }

    public void eliminar(Integer id) {
        OrdenServicio o = obtener(id);
        if (o.getEstado() != EstadoOrden.RECIBIDO) {
            throw new IllegalArgumentException("Solo se puede eliminar una orden en estado RECIBIDO");
        }
        repo.delete(o);
    }

    private void copiar(OrdenServicioDTO dto, OrdenServicio o) {
        o.setIdEquipo(dto.getIdEquipo());
        o.setFechaIngreso(dto.getFechaIngreso());
        o.setDescripcion(dto.getDescripcion());
        o.setCostoEstimado(dto.getCostoEstimado());
    }
}
