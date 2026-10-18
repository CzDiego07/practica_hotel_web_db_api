package com.practica.hoteles_web_api.dtos;

import java.math.BigDecimal;

public interface habitacionByEstado {
    //Tabla Hotel
    String getHotel(); // AS hotel

    //Tabla habitaciones 
    String getHabitacion(); // AS habitacion
    Integer getPiso(); // AS  piso

    //Tabla Tipo
    String getTipo(); //AS tipo
    Integer getCapacidad(); // AS capacidad
    BigDecimal getPrecio(); // AS precio
}