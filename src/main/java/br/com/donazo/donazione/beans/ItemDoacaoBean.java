package br.com.donazo.donazione.beans;

import br.com.donazo.donazione.entities.ItemDoacao;
import br.com.donazo.donazione.repositorios.ItemDoacaoRepository;
import br.com.donazo.donazione.utils.MessagesUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ItemDoacaoBean {

	private ItemDoacaoRepository repository;

	private List<ItemDoacao> itensDoacao;

	private ItemDoacao itemDoacao;
	
	public ItemDoacaoBean() {
		
	}
	
	@Autowired
	private ItemDoacaoBean(ItemDoacaoRepository repository, ItemDoacao itemDoacao) {
		this.repository = repository; this.itemDoacao = itemDoacao;
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
