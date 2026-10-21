package com.practica.hoteles_web_api.controllers;

import com.practica.hoteles_web_api.services.HotelAssistantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/hotel-ai")
public class HotelAiController {

    private final HotelAssistantService assistantService;

    public HotelAiController(HotelAssistantService assistantService) {
        this.assistantService = assistantService;
    }

    @PostMapping("/ask")
    public ResponseEntity<Map<String, String>> preguntar(@RequestBody Map<String, String> request) {
        String pregunta = request.get("pregunta");

        if (pregunta == null || pregunta.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "La pregunta no puede estar vacía."));
        }

        String respuesta = assistantService.preguntar(pregunta);
        return ResponseEntity.ok(Map.of("respuesta", respuesta));
    }
}