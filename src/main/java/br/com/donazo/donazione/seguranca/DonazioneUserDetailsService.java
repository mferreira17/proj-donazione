package br.com.donazo.donazione.seguranca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.donazo.donazione.entities.Colaborador;
import br.com.donazo.donazione.entities.Grupo;
import br.com.donazo.donazione.entities.Permissao;
import br.com.donazo.donazione.repositorios.ColaboradorRepository;
import br.com.donazo.donazione.repositorios.GrupoRepository;
import br.com.donazo.donazione.repositorios.PermissaoRepository;

@Service
public class DonazioneUserDetailsService implements UserDetailsService {

	@Autowired
	private ColaboradorRepository colaboradorRepository;

	@Autowired
	private PermissaoRepository permissaoRepository;

	@Autowired
	private GrupoRepository grupoRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Colaborador colaborador = colaboradorRepository.findByEmail(username);

		if (colaborador == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}

		return new UsuarioSistema(colaborador.getNome(), colaborador.getEmail(), colaborador.getSenha(),
				authorities(colaborador));
	}

	public Collection<? extends GrantedAuthority> authorities(Colaborador colaborador) {
		return authorities(grupoRepository.findByColaboradoresIn(colaborador));
	}

	public Collection<? extends GrantedAuthority> authorities(List<Grupo> grupos) {
		Collection<GrantedAuthority> auths = new ArrayList<>();

		for (Grupo grupo : grupos) {
			List<Permissao> lista = permissaoRepository.findByGruposIn(grupo);

			for (Permissao permissao : lista) {
				auths.add(new SimpleGrantedAuthority("ROLE_" + permissao.getNome()));
			}
		}

		return auths;
	}

}
