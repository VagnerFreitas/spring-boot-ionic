package com.vagner.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vagner.cursomc.domain.Categoria;
import com.vagner.cursomc.domain.Cidade;
import com.vagner.cursomc.domain.Cliente;
import com.vagner.cursomc.domain.Endereco;
import com.vagner.cursomc.domain.Estado;
import com.vagner.cursomc.domain.Produto;
import com.vagner.cursomc.domain.enums.TipoCliente;
import com.vagner.cursomc.repositories.CategoriaRepository;
import com.vagner.cursomc.repositories.CidadeRepository;
import com.vagner.cursomc.repositories.ClienteRepository;
import com.vagner.cursomc.repositories.EnderecoRepository;
import com.vagner.cursomc.repositories.EstadoRepository;
import com.vagner.cursomc.repositories.ProdutoRepository;

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
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		
		p1.getCategorias().add(cat1);
		p3.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().add(p2);
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null,"São Paulo");
		Estado est2 = new Estado(null,"Minas Gerais");
		
		Cidade c1 = new Cidade(null,"São Paulo", est1);
		Cidade c2 = new Cidade(null,"Campinas",est1);
		Cidade c3 = new Cidade(null,"Uberlandia",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1,c2));
		est2.getCidades().add(c3);
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838383"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c2);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "centro", "38777012", cli1, c1);
		 
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
	}

}
