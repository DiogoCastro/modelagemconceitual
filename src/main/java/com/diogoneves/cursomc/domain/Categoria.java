package com.diogoneves.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/*
 * CHECKLIST PARA CRIAR ENTIDADES:
 * 		• Atributos Básicos
 * 		• Associações(Inicie as coleções) - não se aplica ainda
 * 		• Construtores 
 * 		• Getters e Setters
 * 		• hashcode e equals (implementação padrão: SOMENTE id)
 * 		• Serializable (padrão 1L)
 * */

@Entity
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  // Estratégia de geração automática dos IDs das categorias
	private Integer id;
	private String nome;
	
	@ManyToMany(mappedBy="categorias")  // "mappedBy" faz com que não precise informar tudo que já havia em "categorias", dentro da classe Produto
	private List<Produto> produtos = new ArrayList<>();

	public Categoria() {}

	public Categoria(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
