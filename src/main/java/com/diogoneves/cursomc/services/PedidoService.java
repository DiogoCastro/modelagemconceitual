package com.diogoneves.cursomc.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diogoneves.cursomc.domain.ItemPedido;
import com.diogoneves.cursomc.domain.PagamentoComBoleto;
import com.diogoneves.cursomc.domain.Pedido;
import com.diogoneves.cursomc.domain.enums.EstadoPagamento;
import com.diogoneves.cursomc.repositories.ClienteRepository;
import com.diogoneves.cursomc.repositories.ItemPedidoRepository;
import com.diogoneves.cursomc.repositories.PagamentoRepository;
import com.diogoneves.cursomc.repositories.PedidoRepository;
import com.diogoneves.cursomc.repositories.ProdutoRepository;
import com.diogoneves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired  // Permite a dependência a ser automaticamente instanciada pelo Spring
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EmailService emailService;
	
	public Pedido find(Integer id) {
		 Pedido obj = repo.findOne(id);
		 if (obj == null) {
			 throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: " + Pedido.class.getName());
		 }
		 return obj;
	}	
	public List<Pedido> findAll(){
		return repo.findAll();
	}
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteRepository.findOne(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoRepository.findOne(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.save(obj.getItens());
		System.out.println(obj);
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}
}
