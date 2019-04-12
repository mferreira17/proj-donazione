package br.com.donazo.donazione.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.donazo.donazione.entities.Campanha;
import br.com.donazo.donazione.entities.Doacao;
import br.com.donazo.donazione.entities.Meta;
import br.com.donazo.donazione.repositorios.DoacaoRepository;

@Named
@RequestScoped
public class CampanhaBean {

	private Campanha campanha;
	private Meta meta;
	
	@Inject
	private DoacaoBean doacaoBean;
	
	private DualListModel<Doacao> doacoesDualList;
	private DualListModel<String> acoesDualList;

	@PostConstruct
	public void init() {
		campanha = new Campanha(); meta = new Meta();
		
		doacoesDualList = new DualListModel<>(doacaoBean.getDoacoes(), new ArrayList<>());
		
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
	
	public DualListModel<Doacao> getDoacoesDualList() {
		return doacoesDualList;
	}

	public void setDoacoesDualList(DualListModel<Doacao> doacoesDualList) {
		this.doacoesDualList = doacoesDualList;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}
	
	

}
