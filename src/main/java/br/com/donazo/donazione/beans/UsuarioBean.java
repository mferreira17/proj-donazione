/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donazo.donazione.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.donazo.donazione.entities.Colaborador;
import br.com.donazo.donazione.entities.Permissao;
import br.com.donazo.donazione.entities.tipo.TipoPerfilUsuario;
import br.com.donazo.donazione.repositorios.ColaboradorRepository;
import br.com.donazo.donazione.repositorios.PermissaoRepository;
import br.com.donazo.donazione.utils.MessagesUtil;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 3832701313445809259L;

	private Colaborador colaborador;

	@Autowired
	private PermissaoRepository permissaoRepository;

	@Autowired
	private ColaboradorRepository colaboradorRepository;

	public void init() {
		colaborador = new Colaborador();
		getColaborador().setAtivo(true);
		getColaborador().setPerfil(TipoPerfilUsuario.COLABORADOR.getValor());
		getColaborador().setPermissoes(permissoesPerfil(getColaborador()));
	}

	public void gravarCadastrar() {

		String senha = getColaborador().getSenha();
		getColaborador().setSenha(new BCryptPasswordEncoder().encode(senha));
		
		if (registroDuplicado(getColaborador())) {
			MessagesUtil.criarMensagemDeErro("Colaborador já cadastrado!");
		} else {
			try {
				this.colaboradorRepository.save(this.getColaborador());
				MessagesUtil.criarMensagemDeInformacao("Colaborador" + this.getColaborador().getNome() + "  salvo.");
				this.limpar();
			} catch (final Exception e) {
				MessagesUtil.criarMensagemDeErro(e.getMessage());
			}
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

	public Boolean registroDuplicado(Colaborador entidade) {
		Colaborador registro = colaboradorRepository.findByEmail(entidade.getEmail());
		if (registro != null) {
			return true;
		} else {
			return false;
		}
		
	}
	public Set<Permissao> permissoesPerfil(Colaborador entidade) {
		permissaoRepository.findByPerfil(entidade.getPerfil());
		Set<Permissao> permissoes = new HashSet<>();
		return permissoes;
	}

	public void limpar() {

		this.colaborador = new Colaborador();

	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

//	public boolean isAdministrador() {
//
//		final Colaborador c = getColaborador();
//		if (c != null) {
//			final TipoPerfilUsuario perfil = TipoPerfilUsuario.obter(c.getPerfil());
//			return perfil.isAdministrador();
//		} else {
//			return false;
//		}
//	}
//
//	public boolean isColaborador() {
//
//		final Colaborador c = getColaborador();
//		if (c != null && !isAdministrador()) {
//			final TipoPerfilUsuario perfil = TipoPerfilUsuario.obter(c.getPerfil());
//			return perfil.isColaborador();
//		} else {
//			return false;
//		}
//	}
//
//	public boolean isVoluntario() {
//
//		final Colaborador c = getColaborador();
//		if (c != null) {
//			final TipoPerfilUsuario perfil = TipoPerfilUsuario.obter(c.getPerfil());
//			return perfil.isVoluntario();
//		} else {
//			return false;
//		}
//	}

}
