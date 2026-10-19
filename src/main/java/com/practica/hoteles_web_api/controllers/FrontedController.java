package com.practica.hoteles_web_api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class FrontedController {
    @GetMapping ("/")
    public String mostrarIndex(){
        return  "index";
    }
    @GetMapping ("/consultas")
    public String mostrarHistorias(){
        return  "consultas";
    }
    @GetMapping ("/hoteles")
    public String mostrarHoteles(){
        return  "hoteles";
    }
    @GetMapping ("/tipos")
    public String mostrarTipos(){
        return  "tipos";
    }
    @GetMapping ("/habitaciones")
    public String mostrarHabitaciones(){
        return  "habitaciones";
    }
}
