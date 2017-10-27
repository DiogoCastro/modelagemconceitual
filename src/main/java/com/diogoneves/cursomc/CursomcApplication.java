package com.diogoneves.cursomc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.diogoneves.cursomc.services.DBService;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	public static void main(String[] args) {
		
		
		
		SpringApplication.run(CursomcApplication.class, args);
	}
	
	@Autowired
	DBService service;
	
	@Override
	public void run(String... args) throws Exception {
		service.instantiateTestDatabase();
	}
}
