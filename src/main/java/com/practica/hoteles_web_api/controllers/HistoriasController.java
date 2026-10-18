package com.practica.hoteles_web_api.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practica.hoteles_web_api.dtos.OfertaHabitacionesForHotel;
import com.practica.hoteles_web_api.dtos.cantidadHabitacionesByTipo;
import com.practica.hoteles_web_api.dtos.habitacionByEstado;
import com.practica.hoteles_web_api.dtos.habitacionPorRangoMinMax;
import com.practica.hoteles_web_api.models.Enum_EstadoHab;
import com.practica.hoteles_web_api.repository.habitacionRepository;

@RestController
@RequestMapping("/api/consultas")
public class HistoriasController {

    @Autowired
    private habitacionRepository habitacionJPA;

    public HistoriasController(habitacionRepository habitacionJPA) {
        this.habitacionJPA = habitacionJPA;
    }

    // 1. Historia: Como encargado de reservaciones, quiero consultar las
    // habitaciones disponibles, para conocer qué
    // habitaciones pueden ser ofrecidas a los huéspedes.
    @GetMapping("/habitacion/disponible")
    public List<habitacionByEstado> obtenerHabitacionesLibres() {
        return habitacionJPA.getHabitacionByEstado(Enum_EstadoHab.Disponible);
    }

    // 2. Historia: Como gerente de la cadena, quiero consultar las habitaciones de
    // cada hotel junto con su tipo y
    // capacidad, para conocer cómo está distribuida la oferta de habitaciones.
    @GetMapping("/oferta")
    public List<OfertaHabitacionesForHotel> obtenerOfertaHabitacionHotel() {
        return habitacionJPA.getOfertaHabitacionesForHotel();
    }

    // 3. Historia: Como gerente de la cadena, quiero consultar las habitaciones de
    // cada hotel junto con su tipo y capacidad,
    // para conocer la oferta de habitaciones unicamente de las disponibles
    @GetMapping("/oferta/disponibles")
    public List<OfertaHabitacionesForHotel> obtenerOfertaHabitacionHotelPorEstado() {
        return habitacionJPA.getOfertaHabitacionesForHotel(Enum_EstadoHab.Disponible);
    }

    // 4. Historia: Como gerente de la cadena, quiero consultar la cantidad de
    // habitaciones por tipo y a que hotel pertenecen
    // para conocer que tipos de habitaciones se tienen en cada hotel
    @GetMapping("/cantidad")
    public List<cantidadHabitacionesByTipo> obtenerCantidadHabitacionesPorTipo() {
        return habitacionJPA.getCantidadHabitacionesByTipo();
    }

    // 5. Historia: Como recepcionista, quiero consultar las habitaciones
    // disponibles por un rango minimo y maximo de precio
    // para ofrecer estas habitaciones a los clientes con un presupuesto limitado
    @GetMapping ("/rangoPrecio")
    public List<habitacionPorRangoMinMax> obtenerHabitacionPorRangoPrecio(@RequestParam BigDecimal min, @RequestParam  BigDecimal max){
        return habitacionJPA.getHabitacionesByRangoPrecio(min, max);
    }
}
