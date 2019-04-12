package br.com.donazo.donazione.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;



import br.com.donazo.donazione.entities.Colaborador;
import br.com.donazo.donazione.repositorios.ColaboradorRepository;
import br.com.donazo.donazione.utils.MessagesUtil;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class ColaboradorBean implements Serializable{

	private Colaborador colaborador;
	
	@Inject
	private ColaboradorRepository colaboradorRespository;
	
	@PostConstruct
	public void init() {
		colaborador = new Colaborador();
		
	}
	
	public void salvar() {
		
		if (colaborador != null) {
			colaboradorRespository.save(colaborador);
			MessagesUtil.criarMensagemDeInformacao("Colaborador Cadastrado Com Sucesso");
			colaborador = new Colaborador(); //recria a entidade para nova pesistencia
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
