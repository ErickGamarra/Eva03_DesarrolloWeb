package com.tecsup.repository;

import com.tecsup.model.EstadoOrden;
import com.tecsup.model.OrdenServicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdenServicioRepository extends JpaRepository<OrdenServicio, Integer> {

    List<OrdenServicio> findByIdEquipo(Integer idEquipo);

    List<OrdenServicio> findByEstado(EstadoOrden estado);
}
