package com.vagner.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vagner.cursomc.domain.ItemPedido;

@Repository
public interface ItemPedidoRespository extends JpaRepository<ItemPedido, Integer>{

	
}
