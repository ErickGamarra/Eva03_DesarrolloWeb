package com.tecsup.repository;

import com.tecsup.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Integer> {

    List<Equipo> findByIdCliente(Integer idCliente);

    boolean existsByCodigo(String codigo);

    boolean existsByCodigoAndIdEquipoNot(String codigo, Integer idEquipo);
}
