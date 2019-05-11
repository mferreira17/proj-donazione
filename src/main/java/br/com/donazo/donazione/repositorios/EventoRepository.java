package br.com.donazo.donazione.repositorios;

import org.springframework.data.repository.CrudRepository;

import br.com.donazo.donazione.entities.Evento;

public interface EventoRepository extends CrudRepository<Evento, Integer> {

}
