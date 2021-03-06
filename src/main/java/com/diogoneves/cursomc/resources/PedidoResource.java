package com.diogoneves.cursomc.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@RequestMapping(method=RequestMethod.GET)  // Procurar mais sobre os verbos REST
	public List<Pedido> findAll() {  
		// ResponseEntity armazena várias informações de uma resposta Http para um serviço  REST
		
		
		return service.findAll();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
