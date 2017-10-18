package com.diogoneves.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diogoneves.cursomc.domain.Pedido;
import com.diogoneves.cursomc.repositories.PedidoRepository;
import com.diogoneves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired  // Permite a dependência a ser automaticamente instanciada pelo Spring
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		 Pedido obj = repo.findOne(id);
		 if (obj == null) {
			 throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: " + Pedido.class.getName());
		 }
		 return obj;
	}	
	
}
