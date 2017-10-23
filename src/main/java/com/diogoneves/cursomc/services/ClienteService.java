package com.diogoneves.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.diogoneves.cursomc.domain.Cliente;
import com.diogoneves.cursomc.dto.ClienteDTO;
import com.diogoneves.cursomc.repositories.ClienteRepository;
import com.diogoneves.cursomc.services.exceptions.DataIntegrityException;
import com.diogoneves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired  // Permite a dependência a ser automaticamente instanciada pelo Spring
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		 Cliente obj = repo.findOne(id);
		 if (obj == null) {
			 throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: " + Cliente.class.getName());
		 }
		 return obj;
	}	
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir. Há entidades relacionadas.");
		}
	}
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente FromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
