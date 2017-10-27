package com.diogoneves.cursomc.domain;

import javax.persistence.Entity;

import com.diogoneves.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;
	
	public PagamentoComCartao() {}

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido,// Generate constructor from superclass, já que herda de Pagamento, só basta adicionar o campo que consta nessa classe "numeroDeParcelas"
			 Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	// NÃO PRECISA DE HASHCODE EQUALS, PORQUE JÁ CONSTA NA SUPERCLASSE, ENTÃO HERDA AUTOMATICAMENTE
}
