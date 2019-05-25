package br.com.donazo.donazione.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.donazo.donazione.entities.Colaborador;
import br.com.donazo.donazione.repositorios.ColaboradorRepository;
import br.com.donazo.donazione.utils.CrudUtils;
import br.com.donazo.donazione.utils.MessagesUtil;
import br.com.donazo.donazione.utils.UtilBean;

@Named
@ViewScoped
public class ColaboradorBean extends CrudUtils implements Serializable {

	private static final long serialVersionUID = -4216221212973849019L;

	private Colaborador colaborador;

	private Iterable<Colaborador> colaboradores;

	@Autowired
	private ColaboradorRepository colaboradorRepository;

	@PostConstruct
	public void init() {

		this.prepaprar();
		this.colaboradores = this.colaboradorRepository.findAll();
	}

	public void gravarInserir() {

		novoColaborador(getColaborador());
	}

	public void gravarAlterar() {
		alterarColaborador(getColaborador());
	}

	public void gravarExcluir() {

		final int colaborador = Integer.parseInt(UtilBean.obterValor("colaborador"));
		try {
			final Optional<Colaborador> registro = this.colaboradorRepository.findById(colaborador);
			MessagesUtil.criarMensagemDeInformacao("Colaborador " + registro.get().getNome() + "  excluído.");
			this.colaboradorRepository.delete(registro.get());
			this.init();
		} catch (final Exception e) {
			MessagesUtil.criarMensagemDeErro(e.getMessage());
		}

	}

	public List<String> getProfissoes() {
		return colaboradorRepository.findAllByProfissao();

	}

	public void prepaprar() {

		if (this.colaborador == null) {
			this.colaborador = new Colaborador();
		}

	}

//	public void limpar() {
//
//		this.colaborador = new Colaborador();
//
//	}

	public void closeEvent(final CloseEvent closeEvent) {

		this.init();
	}

	/**
	 * @return the colaborador
	 */
	public Colaborador getColaborador() {

		return this.colaborador;
	}

	/**
	 * @param colaborador the colaborador to set
	 */
	public void setColaborador(final Colaborador colaborador) {

		this.colaborador = colaborador;
	}

	/**
	 * @return the colaboradores
	 */
	public Iterable<Colaborador> getColaboradores() {

		return this.colaboradores;
	}

	/**
	 * @param colaboradores the colaboradores to set
	 */
	public void setColaboradores(final Iterable<Colaborador> colaboradores) {

		this.colaboradores = colaboradores;
	}

}
