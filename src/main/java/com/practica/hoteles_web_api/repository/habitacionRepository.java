package com.practica.hoteles_web_api.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.practica.hoteles_web_api.dtos.OfertaHabitacionesForHotel;
import com.practica.hoteles_web_api.dtos.cantidadHabitacionesByTipo;
import com.practica.hoteles_web_api.dtos.habitacionByEstado;
import com.practica.hoteles_web_api.dtos.habitacionPorRangoMinMax;
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

    // Creamos la consulta para saber habitacion por Hotel y estado actual
    // El inner Join Corresponde a como se nombro el la tabla con relacion ManyToOne
    // Para el caso de hoteles (private Hoteles hotel)
    @Query("SELECT " +
            "h.nombre AS hotel, " +
            "ha.numero AS habitacion, " +
            "ha.piso AS piso, " +
            "ha.estado AS estado, " +
            "t.nombre AS tipo, " +
            "t.capacidad AS capacidad, " +
            "t.precio_base AS precio " +
            "FROM Habitaciones ha " +
            "INNER JOIN ha.hotel h " +
            "INNER JOIN ha.tipo t " +
            "ORDER BY h.nombre, ha.numero")
    List<OfertaHabitacionesForHotel> getOfertaHabitacionesForHotel();

    // Creamos la consulta para saber habitacion por Hotel y estado actual
    // El inner Join Corresponde a como se nombro el la tabla con relacion ManyToOne
    // Para el caso de hoteles (private Hoteles hotel)
    // Se nombra la misma funcion, pero en este caso se puede especificar un estado
    // especifico
    @Query("SELECT " +
            "h.nombre AS hotel, " +
            "ha.numero AS habitacion, " +
            "ha.piso AS piso, " +
            "ha.estado AS estado, " +
            "t.nombre AS tipo, " +
            "t.capacidad AS capacidad, " +
            "t.precio_base AS precio " +
            "FROM Habitaciones ha " +
            "INNER JOIN ha.hotel h " +
            "INNER JOIN ha.tipo t " +
            "WHERE ha.estado = :estado " +
            "ORDER BY h.nombre, ha.numero")
    List<OfertaHabitacionesForHotel> getOfertaHabitacionesForHotel(@Param("estado") Enum_EstadoHab estado);

    // Consulta para saber la cantidad de habitaciones que se tienen por Tipo en
    // cada hotel
    @Query("SELECT " +
            "h.nombre AS hotel, " +
            "t.nombre AS tipo, " +
            "COUNT(ha) AS cantidad " +
            "FROM Habitaciones ha " +
            "INNER JOIN ha.hotel h " +
            "INNER JOIN ha.tipo t " +
            "GROUP BY h.id_hotel, h.nombre, t.id_tipo, t.nombre " +
            "ORDER BY h.nombre ASC, t.nombre ASC")
    List<cantidadHabitacionesByTipo> getCantidadHabitacionesByTipo();

    @Query("SELECT " +
            "h.nombre AS hotel, " +
            "t.nombre AS tipo, " +
            "t.capacidad AS capacidad, " +
            "ha.piso AS piso, " +
            "ha.numero AS numero, " +
            "ha.estado AS estado, " +
            "t.precio_base AS precio " +
            "FROM Habitaciones ha " +
            "INNER JOIN ha.hotel h " +
            "INNER JOIN ha.tipo t " +
            "WHERE t.precio_base BETWEEN :precioMin AND :precioMax AND ha.estado = :estado " +
            "ORDER BY t.precio_base ASC, h.nombre ASC")
    List<habitacionPorRangoMinMax> getHabitacionesByRangoPrecio(
            @Param("precioMin") BigDecimal precioMin,
            @Param("precioMax") BigDecimal precioMax,
            @Param("estado") Enum_EstadoHab estado
        );
}
