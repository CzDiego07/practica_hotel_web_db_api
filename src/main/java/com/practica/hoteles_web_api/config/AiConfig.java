package com.practica.hoteles_web_api.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean
    public ChatClient hotelChatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem(
                        """
                                Eres el asistente inteligente oficial de una cadena de hoteles.
                                Responde de manera clara, educada y concisa en idioma español.
                                No inventes habitaciones, disponibilidad ni precios.
                                Utiliza siempre las funciones (Tools) registradas para obtener la información real de la base de datos.
                                Si el usuario solicita opciones por presupuesto, consulta la función de rango de precios.
                                Si el usuairo solicita información que no tenga ninguna relación con la cadena hotelera. Responde: 'No dispongo de dicha informacion, pero si te puedo ayudar a consultar datos de las habitaciones para una reservación.'
                                Al igual, si te preguntan por datos personales de una persona fisica, responde lo siguiente: 'No puedo proporcionarte dicha información'
                                """)
                .defaultFunctions(
                        "obtenerHabitacionesLibres",
                        "obtenerOfertaHabitacionHotel",
                        "obtenerOfertaHabitacionHotelPorEstado",
                        "obtenerCantidadHabitacionesPorTipo",
                        "obtenerHabitacionPorRangoPrecio")
                .build();
    }
}