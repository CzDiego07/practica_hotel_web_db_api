package com.practica.hoteles_web_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelesWebApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelesWebApiApplication.class, args);
		System.out.print(
			"\n\n\n"+
			"--------".repeat(5)+ "\n" +
			"En ejecucion en puerto 8082...." + "\n" +
			"--------".repeat(5)
		);
	}

}
