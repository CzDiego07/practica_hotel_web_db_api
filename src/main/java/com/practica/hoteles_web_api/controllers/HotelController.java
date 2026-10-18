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

import com.practica.hoteles_web_api.models.Hoteles;
import com.practica.hoteles_web_api.repository.hotelRepository;

@RestController
@RequestMapping("/api/hoteles")
public class HotelController {

    @Autowired
    private hotelRepository hotelJPA;

    public HotelController(hotelRepository hotelJPA) {
        this.hotelJPA = hotelJPA;
    }

    // Consultar - Obtener todos
    @GetMapping
    public List<Hoteles> obtenerHoteles() {
        return hotelJPA.findAll();
    }
    // Consultar - Obtener hotel por ID
    @GetMapping ("/{id}")
    public Optional<Hoteles> obtenerHotelID(@PathVariable Integer id) {
        return hotelJPA.findById(id);
    }
    // Guardar - Guardar Un nuevo hotel 
    @PostMapping ("/guardar")
    public ResponseEntity<Hoteles> guardarHotel(@RequestBody Hoteles nuevo){
        Hoteles nuevoHotel = hotelJPA.save(nuevo);
        return  ResponseEntity.status(HttpStatus.CREATED).body(nuevoHotel);
    }
    // Actualizar - Actualizar registro de hotel
    @PostMapping ("/actualizar/{id}")
    public ResponseEntity<Hoteles> actualizarHotel(@RequestBody Hoteles cambio,@PathVariable Integer id){
        if(!hotelJPA.existsById(id)){
            return ResponseEntity.notFound().build();
        }else{
            cambio.setId_hotel(id);
            Hoteles cambioHotel = hotelJPA.save(cambio);
            return  ResponseEntity.status(HttpStatus.CREATED).body(cambioHotel);
        }
    }
    // Eliminar - Eliminar un registro de hotel por id
    @DeleteMapping  ("/eliminar/{id}")
    public ResponseEntity<Hoteles> borrarHotel(@PathVariable Integer id){
        if(hotelJPA.existsById(id)){
            hotelJPA.deleteById(id);
            return ResponseEntity.noContent().build();
        }else{
            return  ResponseEntity.notFound().build();
        }
    }
}
