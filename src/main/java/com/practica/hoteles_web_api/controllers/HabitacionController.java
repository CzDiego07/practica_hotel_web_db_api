package com.practica.hoteles_web_api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practica.hoteles_web_api.models.Habitaciones;
import com.practica.hoteles_web_api.repository.habitacionRepository;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {

    @Autowired
    private habitacionRepository habitacionJPA;

    public HabitacionController(habitacionRepository habitacionJPA) {
        this.habitacionJPA = habitacionJPA;
    }

    // Consultar - Obtener todos
    @GetMapping
    public List<Habitaciones> obtenerHabitacion() {
        return habitacionJPA.findAll();
    }

    // Consultar - Obtener hotel por ID
    @GetMapping("/{id}")
    public Optional<Habitaciones> obtenerHabitacionID(@PathVariable Integer id) {
        return habitacionJPA.findById(id);
    }

    // Guardar - Guardar Un nuevo hotel
    @PostMapping("/guardar")
    public ResponseEntity<Habitaciones> guardarHabitacion(@RequestBody Habitaciones nuevo) {
        Habitaciones nuevoHabitacion = habitacionJPA.save(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoHabitacion);
    }

    // Actualizar - Actualizar registro de hotel
    @PostMapping("/actualizar/{id}")
    public ResponseEntity<Habitaciones> actualizarHabitacion(@RequestBody Habitaciones cambio) {
        if (!habitacionJPA.existsById(cambio.getId_habitacion())) {
            return ResponseEntity.notFound().build();
        } else {
            Habitaciones cambioHabitacion = habitacionJPA.save(cambio);
            return ResponseEntity.status(HttpStatus.CREATED).body(cambioHabitacion);
        }
    }

    // Eliminar - Eliminar un registro de hotel por id
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Habitaciones> borrarHabitacion(@PathVariable Integer id) {
        if (!habitacionJPA.existsById(id)) {
            habitacionJPA.deleteById(id);
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
