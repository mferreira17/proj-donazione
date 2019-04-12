package br.com.donazo.donazione.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.donazo.donazione.entities.ItemDoacao;

@Named
@RequestScoped
public class ItemDoacaoBean {
	
	@Autowired
	private ItemDoacaoRepository repository;
	
	private List<ItemDoacao> itensDoacao;
	
	@PostConstruct
	public void init() {
		itensDoacao = (List<ItemDoacao>) repository.findAll();
	}

	public List<ItemDoacao> getItensDoacao() {
		return itensDoacao;
	}

	public void setItensDoacao(List<ItemDoacao> itensDoacao) {
		this.itensDoacao = itensDoacao;
	}
	
	

}
