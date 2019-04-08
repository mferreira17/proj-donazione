package br.com.donazo.donazione.beans;

import java.util.ArrayList;
import java.util.Arrays;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import br.com.donazo.donazione.entities.Acao;
import br.com.donazo.donazione.entities.Campanha;

@Named
@RequestScoped
public class CampanhaBean {
	
	@Inject
	private Campanha campanha;
	
	private DualListModel<Acao> acoesDualList;
	
	
	public void init(){
		acoesDualList = new DualListModel<>(new ArrayList<>(), new ArrayList<>());
	}
	

	public Campanha getCampanha() {
		return campanha;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}
	
	

}
