package br.com.donazo.donazione.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.donazo.donazione.entities.Colaborador;
import br.com.donazo.donazione.entities.Grupo;

public interface GrupoRepository extends CrudRepository<Grupo, Integer> {
	List<Grupo> findByColaboradoresIn(Colaborador colaborador);
}
