package br.com.donazo.donazione.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.donazo.donazione.entities.ItemDoacao;
import br.com.donazo.donazione.repositorios.ItemDoacaoRepository;
import br.com.donazo.donazione.utils.MessagesUtil;

@Named
@RequestScoped
public class ItemDoacaoBean {

	private ItemDoacaoRepository repository;

	private List<ItemDoacao> itensDoacao;

	private ItemDoacao itemDoacao = new ItemDoacao() ;
	
	public ItemDoacaoBean(ItemDoacaoRepository repository) {
		this.repository = repository; 
	}

	@PostConstruct
	private void init() {
		itensDoacao = (List<ItemDoacao>) repository.findAll();
	}

	public void salvar() {
		repository.save(itemDoacao);
		MessagesUtil.criarMensagemDeInformacao("Item de Doação Cadastrado com Sucesso");
		itemDoacao = new ItemDoacao();
	}

	public List<ItemDoacao> getItensDoacao() {
		return itensDoacao;
	}

	public void setItensDoacao(List<ItemDoacao> itensDoacao) {
		this.itensDoacao = itensDoacao;
	}

	public ItemDoacao getItemDoacao() {
		return itemDoacao;
	}

	public void setItemDoacao(ItemDoacao itemDoacao) {
		this.itemDoacao = itemDoacao;
	}
}
