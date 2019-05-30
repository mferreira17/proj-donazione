package br.com.donazo.donazione.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.CloseEvent;
import org.primefaces.model.DualListModel;
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

	private List<String> profissoes;

	private boolean operacao = false;

	@Autowired
	private EventoRepository eventoRepository;
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	private DualListModel<Colaborador> dualListColaboradores;

	@PostConstruct
	public void init() {

		this.prepaprar();
		colaboradores = colaboradorRepository.findAll();
		this.eventos = this.eventoRepository.findAll();
		
		dualListColaboradores = new DualListModel((List<Colaborador>) colaboradores, new ArrayList<>());
		
	}

	public void gravarInserir() {

		try {
			getEvento().setProfissoesConvocadas(listaProfissoes(getProfissoes()));
			Set<Colaborador> colaboradores = dualListColaboradores.getTarget().stream().collect(Collectors.toSet());
			getEvento().setColaboradorList(colaboradores);
			this.eventoRepository.save(this.getEvento());
			MessagesUtil.criarMensagemDeInformacao("Evento " + this.getEvento().getNome() + "  salvo.");
			this.limpar();
		} catch (final Exception e) {
			MessagesUtil.criarMensagemDeErro(e.getMessage());
		}
	}

	public void gravarAlterar() {

		try {
			getEvento().setProfissoesConvocadas(listaProfissoes(getProfissoes()));
			this.eventoRepository.save(this.getEvento());
			MessagesUtil.criarMensagemDeInformacao("Evento " + this.getEvento().getNome() + "  alterado.");
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

	public void getColaboradoresPorProfissao(String profissao) {
		Iterable<Colaborador> findAllByProfissao = colaboradorRepository.findAllByProfissao(profissao);
		getEvento().setColaboradorList((Set<Colaborador>) findAllByProfissao);
	}

	public List<String> completeProfissao(String query) {
		List<String> profissoes = colaboradorRepository.findAllByProfissao();
		List<String> profissoesFiltradas = new ArrayList<>();

		for (int i = 0; i < profissoes.size(); i++) {
			String profissao = profissoes.get(i);
			if (profissao.toLowerCase().contains(query.toLowerCase())) {
				profissoesFiltradas.add(profissao);
			}
		}
		return profissoesFiltradas;
	}

	public String listaProfissoes(List<String> strings) {
		StringBuilder sb = new StringBuilder();
		int size = strings.size();
		for (int i = 0; i < strings.size(); i++) {
			if (i != (size - 1)) {
				sb.append(strings.get(i) + ", ");
			} else {
				sb.append(strings.get(i));
			}
		}
		return sb.toString();
	}

	public void prepaprar() {

		if (this.getEvento() == null) {
			this.limpar();
		}

	}

	public void limpar() {

		evento = new Evento();
		profissoes = new ArrayList<>();
		this.getEvento().setCargaHoraria(1);
		this.getEvento().setCadastro(new Date());
		dualListColaboradores = new DualListModel((List<Colaborador>) colaboradores, new ArrayList<>());


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

	public List<String> getProfissoes() {
		return profissoes;
	}

	public void setProfissoes(List<String> profissoes) {
		this.profissoes = profissoes;
	}

	public boolean isOperacao() {
		return operacao;
	}

	public void setOperacao(boolean operacao) {
		this.operacao = operacao;
	}

	public DualListModel<Colaborador> getDualListColaboradores() {
		return dualListColaboradores;
	}

	public void setDualListColaboradores(DualListModel<Colaborador> dualListColaboradores) {
		this.dualListColaboradores = dualListColaboradores;
	}

}
