package br.com.donazo.donazione.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.donazo.donazione.entities.Doacao;
import br.com.donazo.donazione.repositorios.DoacaoRepository;

@Named
@RequestScoped
public class DoacaoBean {
	
	private List<Doacao> doacoes;
	
	@Autowired
	private DoacaoRepository doacaoRepository;
	
	@PostConstruct
	public void init() {
		doacoes = (List<Doacao>) doacaoRepository.findAll(); 
	}

	public List<Doacao> getDoacoes() {
		return doacoes;
	}

	public void setDoacoes(List<Doacao> doacoes) {
		this.doacoes = doacoes;
	}
}
