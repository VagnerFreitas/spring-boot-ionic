package com.vagner.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vagner.cursomc.domain.Categoria;
import com.vagner.cursomc.domain.Produto;
import com.vagner.cursomc.repositories.CategoriaRepository;
import com.vagner.cursomc.repositories.ProdutoRepository;
import com.vagner.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id) {
		Produto produto = repo.findById(id).orElse(null);
		if (produto == null) {
			throw new ObjectNotFoundException("Objeto não encontrado id: " + id
					+ " Tipo: " + Produto.class.getName());
		}
		return repo.findById(id).orElse(null);
	}
	
	public Page<Produto> search(String nome,List<Integer> ids,Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome,categorias,pageRequest);
	}
}
