package com.vagner.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.vagner.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	
	public void preenchePagamentoComBoleto(PagamentoComBoleto pagto,Date instanteDoPedido) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(instanteDoPedido);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(calendar.getTime());
	}
}
