package com.practica.hoteles_web_api.tools;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import com.practica.hoteles_web_api.dtos.OfertaHabitacionesForHotel;
import com.practica.hoteles_web_api.dtos.cantidadHabitacionesByTipo;
import com.practica.hoteles_web_api.dtos.habitacionByEstado;
import com.practica.hoteles_web_api.dtos.habitacionPorRangoMinMax;
import com.practica.hoteles_web_api.models.Enum_EstadoHab;
import com.practica.hoteles_web_api.repository.habitacionRepository;

@Configuration
public class HotelConsultasTool {

    private final habitacionRepository habitacionJPA;

    public HotelConsultasTool(habitacionRepository habitacionJPA) {
        this.habitacionJPA = habitacionJPA;
    }

    // DTOs auxiliares para entradas de las funciones
    public record RangoPrecioRequest(BigDecimal min, BigDecimal max) {}
    public record EmptyRequest() {}

    // 1. Consulta: Habitaciones Libres/Disponibles
    @Bean
    @Description("Consulta la lista de habitaciones disponibles en los hoteles activos.")
    public Function<EmptyRequest, List<habitacionByEstado>> obtenerHabitacionesLibres() {
        return request -> habitacionJPA.getHabitacionByEstado(Enum_EstadoHab.Disponible);
    }

    // 2. Consulta: Oferta Global de Habitaciones por Hotel
    @Bean
    @Description("Consulta la oferta general de habitaciones de cada hotel junto con su tipo, estado, piso y precio base.")
    public Function<EmptyRequest, List<OfertaHabitacionesForHotel>> obtenerOfertaHabitacionHotel() {
        return request -> habitacionJPA.getOfertaHabitacionesForHotel();
    }

    // 3. Consulta: Oferta Solo de Habitaciones Disponibles por Hotel
    @Bean
    @Description("Consulta la oferta de habitaciones únicamente de aquellas que están con estado Disponible.")
    public Function<EmptyRequest, List<OfertaHabitacionesForHotel>> obtenerOfertaHabitacionHotelPorEstado() {
        return request -> habitacionJPA.getOfertaHabitacionesForHotel(Enum_EstadoHab.Disponible);
    }

    // 4. Consulta: Cantidad de habitaciones por tipo y hotel
    @Bean
    @Description("Consulta la cantidad y conteo de habitaciones agrupadas por tipo/categoría y a qué hotel pertenecen.")
    public Function<EmptyRequest, List<cantidadHabitacionesByTipo>> obtenerCantidadHabitacionesPorTipo() {
        return request -> habitacionJPA.getCantidadHabitacionesByTipo();
    }

    // 5. Consulta: Habitaciones por rango de precio (Min y Max)
    @Bean
    @Description("Consulta que permite obtener las habitaciones disponibles filtradas por un un rango minimo y maximo de precio o presupuesto")
    public Function<RangoPrecioRequest, List<habitacionPorRangoMinMax>> obtenerHabitacionPorRangoPrecio() {
        return request -> habitacionJPA.getHabitacionesByRangoPrecio(
                request.min(), 
                request.max(), 
                Enum_EstadoHab.Disponible
        );
    }
}