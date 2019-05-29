package br.com.donazo.donazione.beans;

import java.io.Serializable;
import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.donazo.donazione.entities.Colaborador;
import br.com.donazo.donazione.entities.Doacao;
import br.com.donazo.donazione.entities.tipo.TipoPerfilUsuario;
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

		limpar();
		if (usuarioBean.getColaboradorLogado() != null) {
			doacao.setColaborador(usuarioBean.getColaboradorLogado());
		} else {
			doacao.setColaborador(new Colaborador());
		}
		doacao.setCadastro(new Date());
	}

	public void gravarInserir() {

		if (isColaborador(getDoacao().getColaborador())) {
			realizarDoacao(getDoacao());
		} else {
			try {
				if (getDoacao().getColaborador().getPerfil() == null) {
					getDoacao().getColaborador().setPerfil(TipoPerfilUsuario.COLABORADOR.getValor());
					getDoacao().getColaborador().setPermissoes(permissoesPerfil(getDoacao().getColaborador()));
				}
				this.colaboradorRepository.save(getDoacao().getColaborador());
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
			limpar();
		} catch (Exception e) {
			MessagesUtil.criarMensagemDeErro(e.getMessage());
		}
	}

	private void limpar() {
		doacao = new Doacao();
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

}
