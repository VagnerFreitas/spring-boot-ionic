package com.vagner.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vagner.cursomc.domain.ItemPedido;
import com.vagner.cursomc.domain.PagamentoComBoleto;
import com.vagner.cursomc.domain.Pedido;
import com.vagner.cursomc.domain.enums.EstadoPagamento;
import com.vagner.cursomc.repositories.ItemPedidoRespository;
import com.vagner.cursomc.repositories.PagamentoRepository;
import com.vagner.cursomc.repositories.PedidoRepository;
import com.vagner.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRespository itemPedidoRespository;
	
	public Pedido find(Integer id) {
		Pedido categoria = repo.findById(id).orElse(null);
		if (categoria == null) {
			throw new ObjectNotFoundException("Objeto não encontrado id: " + id
					+ " Tipo: " + Pedido.class.getName());
		}
		return repo.findById(id).orElse(null);
	}
	
	@Transactional
	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		
		if(pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preenchePagamentoComBoleto(pagto,pedido.getInstante());
		}
		
		repo.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());
		
		for (ItemPedido item : pedido.getItens()) {
			item.setDesconto(0d);
			item.setPreco(produtoService.find(item.getProduto().getId()).getPreco());			
			item.setPedido(pedido);
		}
		
		itemPedidoRespository.saveAll(pedido.getItens());
		return pedido;
	}
}
