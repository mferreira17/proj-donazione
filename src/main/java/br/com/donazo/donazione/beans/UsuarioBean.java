/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donazo.donazione.beans;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.donazo.donazione.entities.Colaborador;
import br.com.donazo.donazione.entities.tipo.TipoPerfilUsuario;
import br.com.donazo.donazione.repositorios.ColaboradorRepository;
import br.com.donazo.donazione.utils.CrudUtils;
import br.com.donazo.donazione.utils.MessagesUtil;

@Named
@ViewScoped
public class UsuarioBean extends CrudUtils implements Serializable {

	private static final long serialVersionUID = 3832701313445809259L;

	private Colaborador colaborador;

	private String senhaNova;

	@Autowired
	private ColaboradorRepository colaboradorRepository;

	public void init() {
		colaborador = new Colaborador();
		getColaborador().setAtivo(true);
		getColaborador().setPerfil(TipoPerfilUsuario.COLABORADOR.getValor());
	}

	public void gravarCadastrar() {

		novoColaborador(getColaborador());
		limpar();
	}

	public void gravarAlterarSenha() {
		Colaborador colaboradorLogador = colaboradorRepository.findByEmail(getColaboradorLogado().getEmail());
		String senhaNova = new BCryptPasswordEncoder().encode(getSenhaNova());

		if (colaboradorLogador != null && getSenhaNova() != null) {
			colaboradorLogador.setSenha(senhaNova);
			try {
				this.colaboradorRepository.save(colaboradorLogador);
				MessagesUtil.criarMensagemDeInformacao("Senha alterada com sucesso.");
				this.limpar();
			} catch (final Exception e) {
				MessagesUtil.criarMensagemDeErro(e.getMessage());
			}
		} else {

			MessagesUtil.criarMensagemDeErro("Senha antiga incorreta!");
		}
	}

	public Colaborador getColaboradorLogado() {

		Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null) {
			Colaborador obj = colaboradorRepository.findByEmail(authentication.getName());

			if (obj instanceof Colaborador) {
				return (Colaborador) obj;
			}
		}
		return null;
	}

	public String getPerfilDoUsuarioLogado() {

		final Colaborador c = getColaboradorLogado();
		if (c != null) {
			final TipoPerfilUsuario perfil = TipoPerfilUsuario.obter(c.getPerfil());
			return perfil.getDescricao();
		}
		return null;
	}

	public void limpar() {

		this.colaborador = new Colaborador();
		if (getSenhaNova() != null) {
			this.senhaNova = new String();

		}
	}

	public void preparar() {

		this.senhaNova = new String();

	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}

	public boolean isAdministrador() {
		Character perfil = getColaboradorLogado().getPerfil();
		if (perfil == TipoPerfilUsuario.ADMINISTRADOR.getValor()) {
			return true;
		} else {
			return false;
		}
	}

//	public boolean isColaborador() {
//		if (getColaborador().getPerfil() == TipoPerfilUsuario.COLABORADOR.getValor()) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	public boolean isVoluntario() {
//		if (getColaborador().getPerfil() == TipoPerfilUsuario.VOLUNTARIO.getValor()) {
//			return true;
//		} else {
//			return false;
//		}
//	}

}
