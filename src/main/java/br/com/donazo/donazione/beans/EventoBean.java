package br.com.donazo.donazione.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.donazo.donazione.entities.Colaborador;
import br.com.donazo.donazione.entities.Evento;
import br.com.donazo.donazione.repositorios.ColaboradorRepository;
import br.com.donazo.donazione.repositorios.EventoRepository;
import br.com.donazo.donazione.utils.MessagesUtil;
import br.com.donazo.donazione.utils.UtilBean;

@Named
@ViewScoped
public class EventoBean implements Serializable {

	private static final long serialVersionUID = -858774744832095173L;

	private Evento evento;

	private Iterable<Evento> eventos;

	private Iterable<Colaborador> colaboradores;

	private boolean operacao = false;

	@Autowired
	private EventoRepository eventoRepository;
	@Autowired
	private ColaboradorRepository colaboradorRepository;

	@PostConstruct
	public void init() {

		this.prepaprar();
		colaboradores = colaboradorRepository.findAll();
		this.eventos = this.eventoRepository.findAll();
	}

	public void gravarInserir() {

		try {
			this.eventoRepository.save(this.evento);
			MessagesUtil.criarMensagemDeInformacao("Evento " + this.evento.getNome() + "  salvo.");
			this.limpar();
		} catch (final Exception e) {
			MessagesUtil.criarMensagemDeErro(e.getMessage());
		}
	}

	public void gravarAlterar() {

		try {
			this.eventoRepository.save(this.evento);
			MessagesUtil.criarMensagemDeInformacao("Evento " + this.evento.getNome() + "  alterado.");
			this.limpar();
			this.setOperacao(true);
		} catch (final Exception e) {
			MessagesUtil.criarMensagemDeErro(e.getMessage());
		}
	}

	public void gravarExcluir() {

		final int evento = Integer.parseInt(UtilBean.obterValor("evento"));
		try {
			final Optional<Evento> registro = this.eventoRepository.findById(evento);
			MessagesUtil.criarMensagemDeInformacao("Evento " + registro.get().getNome() + "  excluído.");
			this.eventoRepository.delete(registro.get());
			this.init();
		} catch (final Exception e) {
			MessagesUtil.criarMensagemDeErro(e.getMessage());
		}

	}
	
	public void getColaboradoresPorProfissao(String profissao){
		Iterable<Colaborador> findAllByProfissao = colaboradorRepository.findAllByProfissao(profissao);
		evento.setColaboradorList((Set<Colaborador>) findAllByProfissao);
	}

	public void prepaprar() {

		if (this.evento == null) {
			this.limpar();
		}

	}

	public void limpar() {

		this.evento = new Evento();
		this.evento.setCargaHoraria(1);
		this.evento.setCadastro(new Date());

	}

	public void closeEvent(final CloseEvent closeEvent) {

		this.init();
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Iterable<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(Iterable<Evento> eventos) {
		this.eventos = eventos;
	}

	public Iterable<Colaborador> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(Iterable<Colaborador> colaboradores) {
		this.colaboradores = colaboradores;
	}

	public boolean isOperacao() {
		return operacao;
	}

	public void setOperacao(boolean operacao) {
		this.operacao = operacao;
	}

}
