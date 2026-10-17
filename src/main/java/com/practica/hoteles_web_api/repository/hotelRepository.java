package com.practica.hoteles_web_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.hoteles_web_api.models.Hoteles;

public interface hotelRepository extends JpaRepository<Hoteles, Integer> {
}
