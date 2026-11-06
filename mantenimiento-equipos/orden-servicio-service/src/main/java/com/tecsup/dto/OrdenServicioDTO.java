package com.tecsup.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrdenServicioDTO {

    @NotNull(message = "El equipo es obligatorio")
    @Positive(message = "El id del equipo debe ser mayor a cero")
    private Integer idEquipo;

    @NotNull(message = "La fecha de ingreso es obligatoria")
    @PastOrPresent(message = "La fecha de ingreso no puede ser futura")
    private LocalDate fechaIngreso;

    @NotBlank(message = "La descripcion es obligatoria")
    @Size(max = 300, message = "La descripcion no debe pasar de 300 caracteres")
    private String descripcion;

    @NotNull(message = "El costo estimado es obligatorio")
    @PositiveOrZero(message = "El costo estimado no puede ser negativo")
    @Digits(integer = 8, fraction = 2, message = "El costo admite hasta 2 decimales")
    private BigDecimal costoEstimado;

    public Integer getIdEquipo() { return idEquipo; }
    public void setIdEquipo(Integer idEquipo) { this.idEquipo = idEquipo; }

    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public BigDecimal getCostoEstimado() { return costoEstimado; }
    public void setCostoEstimado(BigDecimal costoEstimado) { this.costoEstimado = costoEstimado; }
}
