package com.tecsup.dto;

import jakarta.validation.constraints.*;

public class EquipoDTO {

    @NotBlank(message = "El codigo es obligatorio")
    @Size(max = 20, message = "El codigo no debe pasar de 20 caracteres")
    private String codigo;

    @NotBlank(message = "El tipo es obligatorio")
    private String tipo;

    @NotBlank(message = "La marca es obligatoria")
    private String marca;

    @NotBlank(message = "El modelo es obligatorio")
    private String modelo;

    @NotNull(message = "El cliente es obligatorio")
    @Positive(message = "El id del cliente debe ser mayor a cero")
    private Integer idCliente;

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public Integer getIdCliente() { return idCliente; }
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }
}
