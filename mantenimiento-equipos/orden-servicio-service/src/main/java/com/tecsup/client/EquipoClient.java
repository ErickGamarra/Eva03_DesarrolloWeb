package com.tecsup.client;

import com.tecsup.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class EquipoClient {

    private final RestClient rest;

    public EquipoClient(@Value("${servicios.equipo.url}") String url) {
        this.rest = RestClient.create(url);
    }

    public void verificar(Integer idEquipo) {
        try {
            rest.get().uri("/api/equipos/{id}", idEquipo)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException("Equipo no encontrado con id: " + idEquipo);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> detalle(Integer idEquipo) {
        try {
            return rest.get().uri("/api/equipos/{id}/detalle", idEquipo)
                    .retrieve()
                    .body(Map.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException("Equipo no encontrado con id: " + idEquipo);
        }
    }
}
