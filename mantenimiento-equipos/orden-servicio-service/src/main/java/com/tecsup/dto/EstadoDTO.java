package com.tecsup.dto;

import com.tecsup.model.EstadoOrden;
import jakarta.validation.constraints.NotNull;

public class EstadoDTO {

    @NotNull(message = "El estado es obligatorio")
    private EstadoOrden estado;

    public EstadoOrden getEstado() { return estado; }
    public void setEstado(EstadoOrden estado) { this.estado = estado; }
}
