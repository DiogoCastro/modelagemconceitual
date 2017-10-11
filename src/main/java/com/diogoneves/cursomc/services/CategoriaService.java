package com.diogoneves.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diogoneves.cursomc.domain.Categoria;
import com.diogoneves.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired  // Permite a dependência a ser automaticamente instanciada pelo Spring
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		 Categoria obj = repo.findOne(id);
		 return obj;
	}	
	
}
