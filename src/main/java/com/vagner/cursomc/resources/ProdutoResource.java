package com.vagner.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vagner.cursomc.domain.Produto;
import com.vagner.cursomc.dto.ProdutoDTO;
import com.vagner.cursomc.resources.utils.URL;
import com.vagner.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		return ResponseEntity.ok(service.find(id));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		List<Integer> idsCategorias = URL.decodeIntList(categorias);
		
		Page<Produto> produtos = service.search(URL.decodeParam(nome),idsCategorias,page, linesPerPage,orderBy,direction);
		Page<ProdutoDTO> dtoCategorias = produtos.map(m ->  new ProdutoDTO(m));
		return ResponseEntity.ok().body(dtoCategorias);
	}

}
