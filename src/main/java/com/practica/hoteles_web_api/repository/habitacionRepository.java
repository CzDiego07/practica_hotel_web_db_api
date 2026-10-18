package com.practica.hoteles_web_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.practica.hoteles_web_api.dtos.habitacionByEstado;
import com.practica.hoteles_web_api.models.Enum_EstadoHab;
import com.practica.hoteles_web_api.models.Habitaciones;

public interface habitacionRepository extends JpaRepository<Habitaciones, Integer> {
    // Consulta 1. Busqueda de habitaciones que tengan un estado especifico
    // Modificado para que se pueda hacer una busqueda segun por estado, sea libre,
    // ocupado o en mantenimiento
    @Query("SELECT " +
            "h.nombre AS hotel, " +
            "ha.numero AS habitacion, " +
            "ha.piso AS piso, " +
            "t.nombre AS tipo, " +
            "t.capacidad AS capacidad ," +
            "t.precio_base AS precio " +
            "FROM Habitaciones ha " +
            "INNER JOIN ha.hotel h " +
            "INNER JOIN ha.tipo t " +
            "WHERE ha.estado = :estado " +
            "AND h.activo = TRUE")
    List<habitacionByEstado> getHabitacionByEstado(@Param("estado") Enum_EstadoHab estado);
}
