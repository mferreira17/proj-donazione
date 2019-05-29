package br.com.donazo.donazione.repositorios;

import org.springframework.data.repository.CrudRepository;

import br.com.donazo.donazione.entities.Colaborador;
import br.com.donazo.donazione.entities.Doacao;

public interface DoacaoRepository extends CrudRepository<Doacao, Integer>{
	Iterable<Doacao> findAllByColaborador(Colaborador colaborador);
}
