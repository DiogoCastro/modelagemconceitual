package com.diogoneves.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.diogoneves.cursomc.domain.Categoria;
import com.diogoneves.cursomc.domain.Cidade;
import com.diogoneves.cursomc.domain.Cliente;
import com.diogoneves.cursomc.domain.Endereco;
import com.diogoneves.cursomc.domain.Estado;
import com.diogoneves.cursomc.domain.ItemPedido;
import com.diogoneves.cursomc.domain.Pagamento;
import com.diogoneves.cursomc.domain.PagamentoComBoleto;
import com.diogoneves.cursomc.domain.PagamentoComCartao;
import com.diogoneves.cursomc.domain.Pedido;
import com.diogoneves.cursomc.domain.Produto;
import com.diogoneves.cursomc.domain.enums.EstadoPagamento;
import com.diogoneves.cursomc.domain.enums.TipoCliente;
import com.diogoneves.cursomc.repositories.CategoriaRepository;
import com.diogoneves.cursomc.repositories.CidadeRepository;
import com.diogoneves.cursomc.repositories.ClienteRepository;
import com.diogoneves.cursomc.repositories.EnderecoRepository;
import com.diogoneves.cursomc.repositories.EstadoRepository;
import com.diogoneves.cursomc.repositories.ItemPedidoRepository;
import com.diogoneves.cursomc.repositories.PagamentoRepository;
import com.diogoneves.cursomc.repositories.PedidoRepository;
import com.diogoneves.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
//		ATRIBUINDO
		Categoria cat1 = new Categoria(null, "Informática");  // Está nulo pois o id é implementado automaticamente
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
//		ASSOCIANDO
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p1.getItens().addAll(Arrays.asList(ip1));
		
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p2.getItens().addAll(Arrays.asList(ip3));
		
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		cli1.getTelefones().addAll(Arrays.asList("27363323", "938238393"));
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		ped1.setPagamento(pagto1);
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		
		ped2.setPagamento(pagto2);
		ped2.getItens().addAll(Arrays.asList(ip3));		
		
//		SALVANDO
		categoriaRepository.save(Arrays.asList(cat1, cat2));  // Salva as categorias do banco
		produtoRepository.save(Arrays.asList(p1, p2, p3));
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1, c2, c3));
		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(e1, e2));
		pedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoRepository.save(Arrays.asList(pagto1, pagto2));
		itemPedidoRepository.save(Arrays.asList(ip1, ip2, ip3));
	}
}
