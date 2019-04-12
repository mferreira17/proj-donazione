package br.com.donazo.donazione.beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.donazo.donazione.entities.Doacao;
import br.com.donazo.donazione.repositorios.DoacaoRepository;

@Named
@ViewScoped
public class DoacaoBean {

	private Doacao doacao;

	@Inject
	private DoacaoRepository doacaoRespository;

	@PostConstruct
	public void init() {
		doacao = new Doacao();
	}

	public void cadastrar() {
		doacaoRespository.save(doacao);
	}

	public void excluir() {
		doacaoRespository.delete(doacao);
	}

	public void listar() {
		doacaoRespository.findAll();
	}

	public Doacao getDoacao() {
		return doacao;
	}

	public void setDoacao(Doacao doacao) {
		this.doacao = doacao;
	}

}
