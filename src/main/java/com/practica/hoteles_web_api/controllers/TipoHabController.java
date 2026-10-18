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

import com.practica.hoteles_web_api.models.Tipo_Habitacion;
import com.practica.hoteles_web_api.repository.tipoHabRepository;

@RestController
@RequestMapping("/api/tipo")
public class TipoHabController {

    @Autowired
    private tipoHabRepository tipoJPA;

    public TipoHabController(tipoHabRepository tipoJPA) {
        this.tipoJPA = tipoJPA;
    }

    // Consultar - Obtener todos
    @GetMapping
    public List<Tipo_Habitacion> obtenerTipoHabitacion() {
        return tipoJPA.findAll();
    }

    // Consultar - Obtener hotel por ID
    @GetMapping("/{id}")
    public Optional<Tipo_Habitacion> obtenerTipoHabitacionID(@PathVariable Integer id) {
        return tipoJPA.findById(id);
    }

    // Guardar - Guardar Un nuevo hotel
    @PostMapping("/guardar")
    public ResponseEntity<Tipo_Habitacion> guardarTipoHabitacion(@RequestBody Tipo_Habitacion nuevo) {
        Tipo_Habitacion nuevaTipoHabitacion = tipoJPA.save(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaTipoHabitacion);
    }

    // Actualizar - Actualizar registro de hotel
    @PostMapping("/actualizar/{id}")
    public ResponseEntity<Tipo_Habitacion> actualizarTipoHabitacion(@RequestBody Tipo_Habitacion cambio, @PathVariable Integer id) {
        if (!tipoJPA.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            cambio.setId_tipo(id);
            Tipo_Habitacion cambioTipoHabitacion = tipoJPA.save(cambio);
            return ResponseEntity.status(HttpStatus.CREATED).body(cambioTipoHabitacion);
        }
    }

    // Eliminar - Eliminar un registro de hotel por id
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Tipo_Habitacion> borrarTipoHabitacion(@PathVariable Integer id) {
        if (tipoJPA.existsById(id)) {
            tipoJPA.deleteById(id);
            return ResponseEntity.noContent().build();
        }else{
            return  ResponseEntity.notFound().build();
        }
    }
}
