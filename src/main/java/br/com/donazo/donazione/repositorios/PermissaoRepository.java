package br.com.donazo.donazione.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.donazo.donazione.entities.Grupo;
import br.com.donazo.donazione.entities.Permissao;

public interface PermissaoRepository extends CrudRepository<Permissao, Integer> {
	List<Permissao> findByGruposIn(Grupo grupo);
}
