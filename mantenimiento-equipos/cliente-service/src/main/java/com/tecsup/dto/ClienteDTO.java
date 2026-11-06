package com.tecsup.dto;

import jakarta.validation.constraints.*;

public class ClienteDTO {

    @NotBlank(message = "El documento es obligatorio")
    @Pattern(regexp = "\\d{8}|\\d{11}", message = "El documento debe ser un DNI (8 digitos) o RUC (11 digitos)")
    private String documento;

    @NotBlank(message = "Los nombres son obligatorios")
    @Size(max = 100, message = "Los nombres no deben pasar de 100 caracteres")
    private String nombres;

    @Pattern(regexp = "9\\d{8}", message = "El telefono debe tener 9 digitos y empezar con 9")
    private String telefono;

    @Email(message = "El correo no tiene un formato valido")
    private String correo;

    @Size(max = 150, message = "La direccion no debe pasar de 150 caracteres")
    private String direccion;

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
}
