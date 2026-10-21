package com.practica.hoteles_web_api.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class HotelAssistantService {

    private final ChatClient chatClient;

    public HotelAssistantService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String preguntar(String pregunta) {
        try {
            String respuesta = chatClient.prompt()
                    .user(pregunta)
                    .call()
                    .content();

            if (respuesta == null || respuesta.isBlank()) {
                return "No pude encontrar una respuesta adecuada para tu consulta. Por favor, intenta reformular tu pregunta sobre nuestros hoteles, habitaciones o tarifas.";
            }

            return respuesta;

        } catch (Exception e) {
            // Captura errores internos, fallos en las herramientas o problemas de comunicación con Ollama
            return "Lo sentimos, ocurrió un problema al procesar tu solicitud (" + e.getMessage() + "). Por favor verifica que la pregunta esté relacionada con la disponibilidad, precios o tipos de habitaciones de la cadena.";
        }
    }
}