package br.com.donazo.donazione.beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;



import br.com.donazo.donazione.entities.Colaborador;
import br.com.donazo.donazione.repositorios.ColaboradorRepository;

@Named
@ViewScoped
public class ColaboradorBean {

	private Colaborador colaborador;
	
	@Inject
	private ColaboradorRepository colaboradorRespository;
	
	@PostConstruct
	public void init() {
		colaborador = new Colaborador();
		
	}
	
	public void cadastrarColaborador() {
		
		if (colaborador != null) {
			colaboradorRespository.save(colaborador);
		} else {
			System.out.println("deu merda");
		}
	}

	public void excluir() {
		colaboradorRespository.delete(colaborador);
	}
	
	public void listar() {
		colaboradorRespository.findAll();
	}
	
	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}
	
}
