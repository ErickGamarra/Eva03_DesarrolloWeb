package com.tecsup.services;

import com.tecsup.client.ClienteClient;
import com.tecsup.dto.EquipoDTO;
import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Equipo;
import com.tecsup.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository repo;

    @Autowired
    private ClienteClient clienteClient;

    public List<Equipo> listar() {
        return repo.findAll();
    }

    public List<Equipo> listarPorCliente(Integer idCliente) {
        return repo.findByIdCliente(idCliente);
    }

    public Equipo obtener(Integer id) {
        return repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Equipo no encontrado con id: " + id));
    }

    public Map<String, Object> detalle(Integer id) {
        Equipo e = obtener(id);
        Map<String, Object> datos = new LinkedHashMap<>();
        datos.put("equipo", e);
        datos.put("cliente", clienteClient.obtener(e.getIdCliente()));
        return datos;
    }

    public Equipo registrar(EquipoDTO dto) {
        if (repo.existsByCodigo(dto.getCodigo())) {
            throw new IllegalArgumentException("Ya existe un equipo con el codigo " + dto.getCodigo());
        }
        clienteClient.obtener(dto.getIdCliente());
        Equipo e = new Equipo();
        copiar(dto, e);
        return repo.save(e);
    }

    public Equipo actualizar(Integer id, EquipoDTO dto) {
        Equipo e = obtener(id);
        if (repo.existsByCodigoAndIdEquipoNot(dto.getCodigo(), id)) {
            throw new IllegalArgumentException("Ya existe otro equipo con el codigo " + dto.getCodigo());
        }
        clienteClient.obtener(dto.getIdCliente());
        copiar(dto, e);
        return repo.save(e);
    }

    public void eliminar(Integer id) {
        repo.delete(obtener(id));
    }

    private void copiar(EquipoDTO dto, Equipo e) {
        e.setCodigo(dto.getCodigo());
        e.setTipo(dto.getTipo());
        e.setMarca(dto.getMarca());
        e.setModelo(dto.getModelo());
        e.setIdCliente(dto.getIdCliente());
    }
}
