package com.vagner.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.vagner.cursomc.domain.Categoria;
import com.vagner.cursomc.repositories.CategoriaRepository;
import com.vagner.cursomc.services.exceptions.DataIntegrityException;
import com.vagner.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Categoria categoria = repo.findById(id).orElse(null);
		if (categoria == null) {
			throw new ObjectNotFoundException("Objeto não encontrado id: " + id
					+ " Tipo: " + Categoria.class.getName());
		}
		return repo.findById(id).orElse(null);
	}
	
	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return repo.save(categoria);
	}
	
	public Categoria update(Categoria categoria) {
		find(categoria.getId());
		return repo.save(categoria);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}
}
