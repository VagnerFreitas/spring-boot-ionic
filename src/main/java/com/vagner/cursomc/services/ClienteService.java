package com.vagner.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vagner.cursomc.domain.Cliente;
import com.vagner.cursomc.repositories.ClienteRepository;
import com.vagner.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Cliente cliente = repo.findById(id).orElse(null);
		if (cliente == null) {
			throw new ObjectNotFoundException("Objeto não encontrado id: " + id
					+ " Tipo: " + Cliente.class.getName());
		}
		return repo.findById(id).orElse(null);
	}
}
