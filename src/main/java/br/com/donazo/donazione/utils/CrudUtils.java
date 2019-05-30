package br.com.donazo.donazione.utils;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.donazo.donazione.entities.Colaborador;
import br.com.donazo.donazione.entities.Permissao;
import br.com.donazo.donazione.entities.tipo.TipoPerfilUsuario;
import br.com.donazo.donazione.repositorios.ColaboradorRepository;
import br.com.donazo.donazione.repositorios.PermissaoRepository;

public class CrudUtils {

	@Autowired
	private PermissaoRepository permissaoRepository;

	@Autowired
	private ColaboradorRepository colaboradorRepository;

	public void novoColaborador(Colaborador entidade) {

		if (entidade.getSenha() != null) {
			entidade.setSenha(new BCryptPasswordEncoder().encode(entidade.getSenha()));
		}
		if (entidade.getPerfil() == null) {
			entidade.setPerfil(TipoPerfilUsuario.COLABORADOR.getValor());
		}
		entidade.setPermissoes(permissoesPerfil(entidade));

		if (isColaborador(entidade)) {
			if(entidade.getSenha() != null) {
				MessagesUtil.criarMensagemDeErro("Email já cadastrado!");
			} else {
				gravarCadastro(entidade);
			}
		} else {
			gravarCadastro(entidade);
		}
	}

	private void gravarCadastro(Colaborador entidade) {
		try {
			this.colaboradorRepository.save(entidade);
			if (entidade.getAtivo()) {
				MessagesUtil.criarMensagemDeInformacao("Colaborador " + entidade.getNome() + "  salvo.");
			}
			entidade = new Colaborador();
		} catch (final Exception e) {
			MessagesUtil.criarMensagemDeErro(e.getMessage());
		}
	}

	public void alterarColaborador(Colaborador entidade) {

		entidade.setPermissoes(permissoesPerfil(entidade));

		try {
			this.colaboradorRepository.save(entidade);
			MessagesUtil.criarMensagemDeInformacao("Colaborador" + entidade.getNome() + "  alterado.");
			entidade = new Colaborador();
		} catch (final Exception e) {
			MessagesUtil.criarMensagemDeErro(e.getMessage());
		}
	}

	public Set<Permissao> permissoesPerfil(Colaborador entidade) {
		Permissao permissao = permissaoRepository.findByPerfil(entidade.getPerfil());
		Set<Permissao> permissoes = new HashSet<>();
		permissoes.add(permissao);
		return permissoes;
	}

	public Boolean isColaborador(Colaborador entidade) {
		Colaborador registro = colaboradorRepository.findByEmail(entidade.getEmail());
		if (registro != null) {
			return true;
		} else {
			return false;
		}

	}

}
