package com.tecsup.repository;

import com.tecsup.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    boolean existsByDocumento(String documento);

    boolean existsByDocumentoAndIdClienteNot(String documento, Integer idCliente);
}
