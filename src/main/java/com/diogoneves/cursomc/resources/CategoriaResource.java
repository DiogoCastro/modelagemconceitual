package com.diogoneves.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.diogoneves.cursomc.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")  //o value é o nome do endpoint
public class CategoriaResource {
	
	@RequestMapping(method=RequestMethod.GET)  // Procurar mais sobre os verbos REST
	public List<Categoria> listar() {
		
		Categoria cat1 = new Categoria(1, "Informática");
		Categoria cat2 = new Categoria(2, "Escritório");
		
		List<Categoria> lista = new ArrayList<>();  // List não pode ser referenciada, poque é uma interface, então é usado o ArrayList
		// Adicionando os dois à lista
		lista.add(cat1);
		lista.add(cat2);
		
		return lista;
	}
}
