package br.com.donazo.donazione.beans;

import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import br.com.donazo.donazione.entities.Campanha;

@Named
@RequestScoped
public class CampanhaBean {

	private Campanha campanha;

	private DualListModel<String> acoesDualList;

	@PostConstruct
	public void init() {
		campanha = new Campanha();
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

}
