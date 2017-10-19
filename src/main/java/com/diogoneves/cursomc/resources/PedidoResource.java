package com.diogoneves.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.diogoneves.cursomc.domain.Pedido;
import com.diogoneves.cursomc.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")  //o value é o nome do endpoint
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)  // Procurar mais sobre os verbos REST
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {  
		//PathVariable faz com que o ID da url vá para o ID da variável
		// ResponseEntity armazena várias informações de uma resposta Http para um serviço  REST
		
		
		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
}