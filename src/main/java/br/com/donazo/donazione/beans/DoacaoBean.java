package br.com.donazo.donazione.beans;

import java.io.Serializable;
import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.donazo.donazione.entities.Colaborador;
import br.com.donazo.donazione.entities.Doacao;
import br.com.donazo.donazione.repositorios.ColaboradorRepository;
import br.com.donazo.donazione.repositorios.DoacaoRepository;
import br.com.donazo.donazione.utils.CrudUtils;
import br.com.donazo.donazione.utils.MessagesUtil;

@Named
@ViewScoped
public class DoacaoBean extends CrudUtils implements Serializable {

	private static final long serialVersionUID = 7097587754010248781L;

	private Iterable<Doacao> doacoes;

	private Doacao doacao;

	private Boolean doacaoParaCampanha = false;
	
	@Autowired
	private UsuarioBean usuarioBean;

	@Autowired
	private DoacaoRepository doacaoRepository;

	@Autowired
	private ColaboradorRepository colaboradorRepository;

//	@PostConstruct
	public void init() {
		doacoes = doacaoRepository.findAll();
	}

	public void initDoacoesPorColaborador() {
		doacoes = doacaoRepository.findAllByColaborador(usuarioBean.getColaboradorLogado());
	}

	public void initNovaDoacao() {

		doacao = new Doacao();
//		if (usuarioBean.getColaboradorLogado() != null) {
//			doacao.setColaborador(usuarioBean.getColaboradorLogado());
//		} else {
		doacao.setColaborador(new Colaborador());
		doacao.getColaborador().setAtivo(false);
		;
//		}
		doacao.setCadastro(new Date());
	}

	public void gravarInserir() {
		String email = getDoacao().getColaborador().getEmail();
		if (isColaborador(getDoacao().getColaborador())) {
			realizarDoacao(getDoacao());
		} else {
			try {
				novoColaborador(getDoacao().getColaborador());
				// Esta sendo setado o email novamente, pois al cadastrar um novo colaborador o
				// metodo da um new Colaborador();
				getDoacao().getColaborador().setEmail(email);
				realizarDoacao(getDoacao());
			} catch (final Exception e) {
				MessagesUtil.criarMensagemDeErro(e.getMessage());
			}
		}
	}

	private void realizarDoacao(Doacao entidade) {
		Colaborador registro = colaboradorRepository.findByEmail(entidade.getColaborador().getEmail());
		entidade.setColaborador(registro);
		try {
			doacaoRepository.save(entidade);
			MessagesUtil.criarMensagemDeInformacao("Doação cadastrada.");
			doacao = new Doacao();
		} catch (Exception e) {
			MessagesUtil.criarMensagemDeErro(e.getMessage());
		}
	}

	public Iterable<Doacao> getDoacoes() {
		return doacoes;
	}

	public void setDoacoes(Iterable<Doacao> doacoes) {
		this.doacoes = doacoes;
	}

	public Doacao getDoacao() {
		return doacao;
	}

	public void setDoacao(Doacao doacao) {
		this.doacao = doacao;
	}

	public Boolean getDoacaoParaCampanha() {
		return doacaoParaCampanha;
	}

	public void setDoacaoParaCampanha(Boolean doacaoParaCampanha) {
		this.doacaoParaCampanha = doacaoParaCampanha;
	}

}
