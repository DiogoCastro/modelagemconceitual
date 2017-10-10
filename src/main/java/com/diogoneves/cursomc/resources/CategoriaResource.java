package com.diogoneves.cursomc.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/categorias")  //o value é o nome do endpoint
public class CategoriaResource {
	
	@RequestMapping(method=RequestMethod.GET)  // Procurar mais sobre os verbos REST
	public String listar() {
		return "REST está funcionando!";
	}
}
