package br.com.donazo.donazione.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.donazo.donazione.entities.Colaborador;

public interface ColaboradorRepository extends CrudRepository<Colaborador, Integer> {
	public Colaborador findByEmail(String email);
	public Colaborador findByEmailAndSenha(String email, String senha);
	public Iterable<Colaborador> findAllByProfissao(String profissao);
	
	@Query(value = "SELECT profissao FROM Colaborador GROUP BY profissao")
	List<String> findAllByProfissao();
}
