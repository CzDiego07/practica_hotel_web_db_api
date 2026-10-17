package com.practica.hoteles_web_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.hoteles_web_api.models.Tipo_Habitacion;

public interface tipoHabRepository extends JpaRepository<Tipo_Habitacion, Integer> {

}
