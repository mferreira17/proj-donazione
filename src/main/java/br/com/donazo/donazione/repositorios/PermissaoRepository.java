package br.com.donazo.donazione.repositorios;

import org.springframework.data.repository.CrudRepository;

import br.com.donazo.donazione.entities.Permissao;

public interface PermissaoRepository extends CrudRepository<Permissao, Integer> {
	Permissao findByPerfil(Character perfil);
}
