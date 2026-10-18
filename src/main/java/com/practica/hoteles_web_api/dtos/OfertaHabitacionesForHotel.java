package com.practica.hoteles_web_api.dtos;

import java.math.BigDecimal;

import com.practica.hoteles_web_api.models.Enum_EstadoHab;

public interface  OfertaHabitacionesForHotel {
    //Tabla Hotel
    String getHotel(); // AS hotel

    //Tabla habitaciones 
    String getHabitacion(); // AS habitacion
    Integer getPiso(); // AS  piso
    Enum_EstadoHab getEstado(); // AS estado

    //Tabla Tipo
    String getTipo(); //AS tipo
    Integer getCapacidad(); // AS capacidad
    BigDecimal getPrecio(); // AS precio
}
