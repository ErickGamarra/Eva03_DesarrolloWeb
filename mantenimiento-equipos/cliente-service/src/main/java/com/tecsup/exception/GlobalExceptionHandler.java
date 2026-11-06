package com.tecsup.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> campos = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> campos.put(e.getField(), e.getDefaultMessage()));
        Map<String, Object> cuerpo = base(HttpStatus.BAD_REQUEST, "Hay datos que no son validos");
        cuerpo.put("errores", campos);
        return ResponseEntity.badRequest().body(cuerpo);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> manejarNoEncontrado(ResourceNotFoundException ex) {
        return respuesta(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> manejarDatoInvalido(IllegalArgumentException ex) {
        return respuesta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> manejarJsonInvalido(HttpMessageNotReadableException ex) {
        return respuesta(HttpStatus.BAD_REQUEST, "El formato de los datos enviados no es correcto");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> manejarTipo(MethodArgumentTypeMismatchException ex) {
        return respuesta(HttpStatus.BAD_REQUEST, "El valor '" + ex.getValue() + "' no es valido para " + ex.getName());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> manejarIntegridad(DataIntegrityViolationException ex) {
        return respuesta(HttpStatus.CONFLICT, "El registro tiene datos repetidos o relacionados");
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<Map<String, Object>> manejarServicioCaido(ResourceAccessException ex) {
        return respuesta(HttpStatus.SERVICE_UNAVAILABLE, "No se pudo conectar con el otro microservicio");
    }

    private ResponseEntity<Map<String, Object>> respuesta(HttpStatus estado, String mensaje) {
        return ResponseEntity.status(estado).body(base(estado, mensaje));
    }

    private Map<String, Object> base(HttpStatus estado, String mensaje) {
        Map<String, Object> cuerpo = new LinkedHashMap<>();
        cuerpo.put("fecha", LocalDateTime.now().withNano(0).toString());
        cuerpo.put("codigo", estado.value());
        cuerpo.put("mensaje", mensaje);
        return cuerpo;
    }
}
