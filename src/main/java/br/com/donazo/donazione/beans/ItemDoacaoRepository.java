package br.com.donazo.donazione.beans;

import org.springframework.data.repository.CrudRepository;

import br.com.donazo.donazione.entities.ItemDoacao;

public interface ItemDoacaoRepository extends CrudRepository<ItemDoacao, Integer> {

}
