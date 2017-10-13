package com.diogoneves.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diogoneves.cursomc.domain.Cliente;
import com.diogoneves.cursomc.repositories.ClienteRepository;
import com.diogoneves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired  // Permite a dependência a ser automaticamente instanciada pelo Spring
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		 Cliente obj = repo.findOne(id);
		 if (obj == null) {
			 throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: " + Cliente.class.getName());
		 }
		 return obj;
	}	
	
}
