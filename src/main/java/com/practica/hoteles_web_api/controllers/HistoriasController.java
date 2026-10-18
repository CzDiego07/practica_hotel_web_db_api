package com.practica.hoteles_web_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practica.hoteles_web_api.dtos.habitacionByEstado;
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
}
