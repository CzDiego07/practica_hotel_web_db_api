package com.practica.hoteles_web_api.dtos;

import java.math.BigDecimal;

public interface habitacionPorRangoMinMax {
    String getHotel();        // AS hotel
    String getTipo();         // AS tipo
    Integer getCapacidad();   // AS capacidad
    Integer getPiso();        // AS piso
    String getNumero();       // AS numero
    BigDecimal getPrecio();   // AS precio
}
