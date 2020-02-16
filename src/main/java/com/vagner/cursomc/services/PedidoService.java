package com.vagner.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vagner.cursomc.domain.Pedido;
import com.vagner.cursomc.repositories.PedidoRepository;
import com.vagner.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Pedido categoria = repo.findById(id).orElse(null);
		if (categoria == null) {
			throw new ObjectNotFoundException("Objeto não encontrado id: " + id
					+ " Tipo: " + Pedido.class.getName());
		}
		return repo.findById(id).orElse(null);
	}
}
