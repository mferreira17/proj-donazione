package br.com.donazo.donazione.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import br.com.donazo.donazione.entities.Campanha;
import br.com.donazo.donazione.entities.Doacao;
import br.com.donazo.donazione.entities.ItemDoacao;
import br.com.donazo.donazione.entities.Meta;

@Named
@RequestScoped
public class CampanhaBean {

	private Campanha campanha;
	private Meta meta;
	
	private DualListModel<String> acoesDualList;
	
	private List<ItemDoacao> doacoes ;

	@PostConstruct
	public void init() {
		campanha = new Campanha(); meta = new Meta();
		acoesDualList = new DualListModel<>(
				Arrays.asList("ACAO DE ENTREGA 1", "ACAO DE ENTREGA 2", "ACAO DE ENTREGA 3"), new ArrayList<>());
	}
	

	public Campanha getCampanha() {
		return campanha;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}

	public DualListModel<String> getAcoesDualList() {
		return acoesDualList;
	}

	public void setAcoesDualList(DualListModel<String> acoesDualList) {
		this.acoesDualList = acoesDualList;
	}

	public List<ItemDoacao> getDoacoes() {
		return doacoes;
	}

	public void setDoacoes(List<ItemDoacao> doacoes) {
		this.doacoes = doacoes;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}
	
	

}
