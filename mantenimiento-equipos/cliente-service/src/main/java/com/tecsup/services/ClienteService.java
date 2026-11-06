package com.tecsup.services;

import com.tecsup.dto.ClienteDTO;
import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Cliente;
import com.tecsup.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public List<Cliente> listar() {
        return repo.findAll();
    }

    public Cliente obtener(Integer id) {
        return repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));
    }

    public Cliente registrar(ClienteDTO dto) {
        if (repo.existsByDocumento(dto.getDocumento())) {
            throw new IllegalArgumentException("Ya existe un cliente con el documento " + dto.getDocumento());
        }
        Cliente c = new Cliente();
        copiar(dto, c);
        return repo.save(c);
    }

    public Cliente actualizar(Integer id, ClienteDTO dto) {
        Cliente c = obtener(id);
        if (repo.existsByDocumentoAndIdClienteNot(dto.getDocumento(), id)) {
            throw new IllegalArgumentException("Ya existe otro cliente con el documento " + dto.getDocumento());
        }
        copiar(dto, c);
        return repo.save(c);
    }

    public void eliminar(Integer id) {
        repo.delete(obtener(id));
    }

    private void copiar(ClienteDTO dto, Cliente c) {
        c.setDocumento(dto.getDocumento());
        c.setNombres(dto.getNombres());
        c.setTelefono(dto.getTelefono());
        c.setCorreo(dto.getCorreo());
        c.setDireccion(dto.getDireccion());
    }
}
