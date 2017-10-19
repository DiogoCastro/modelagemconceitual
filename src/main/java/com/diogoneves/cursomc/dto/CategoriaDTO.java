package com.diogoneves.cursomc.dto;

import java.io.Serializable;

import com.diogoneves.cursomc.domain.Categoria;

public class CategoriaDTO implements Serializable {  // Classe para garantir que apenas as categorias apareçam, impedindo que apareçam outras listas relacionadas a elas.
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	public CategoriaDTO() {}
	
	public CategoriaDTO(Categoria obj) {  //Criar construtor que receba os objetos da entidade de domínio.
		id = obj.getId();
		nome = obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
}
