package br.com.donazo.donazione;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.donazo.donazione.services.PagSeguroService;
import br.com.uol.pagseguro.api.common.domain.DataList;
import br.com.uol.pagseguro.api.transaction.search.TransactionSummary;

@SpringBootTest
@RunWith(value = SpringRunner.class)
public class PagSeguroTest {

	@Autowired
	private PagSeguroService pagSeguroService;
	
	@Test
	public void testBuscarPagamentosPorData() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2019, Calendar.APRIL, 1);
		
		Date dataInicial =  calendar.getTime();
		
		calendar.set(2019, Calendar.MAY, 1);
		Date dataFinal =  calendar.getTime();
		
		DataList<TransactionSummary> data = pagSeguroService.getPagamentosPorData(dataInicial, dataFinal);
		
		assertTrue(data.getData().size()>0);
	}

}
