package br.com.donazo.donazione.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.common.domain.builder.DateRangeBuilder;
import br.com.uol.pagseguro.api.transaction.search.TransactionSummary;

@Service
public class PagSeguroService {

	private PagSeguro pagSeguro;

	public PagSeguroService(PagSeguro pagSeguro) {
		this.pagSeguro = pagSeguro;
	}

	@SuppressWarnings("unchecked")
	public List<TransactionSummary> getPagamentosPorData(Date dataInicial, Date dataFinal) {
		DateRangeBuilder builder = new DateRangeBuilder();
		builder.between(dataInicial, dataFinal);
		List<TransactionSummary> data = (List<TransactionSummary>) pagSeguro.transactions().search()
				.byDateRange(builder, 1, 10).getData();
		return data;
	}

}
