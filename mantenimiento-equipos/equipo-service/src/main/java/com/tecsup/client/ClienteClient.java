package com.tecsup.client;

import com.tecsup.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class ClienteClient {

    private final RestClient rest;

    public ClienteClient(@Value("${servicios.cliente.url}") String url) {
        this.rest = RestClient.create(url);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> obtener(Integer idCliente) {
        try {
            return rest.get().uri("/api/clientes/{id}", idCliente)
                    .retrieve()
                    .body(Map.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException("Cliente no encontrado con id: " + idCliente);
        }
    }
}
