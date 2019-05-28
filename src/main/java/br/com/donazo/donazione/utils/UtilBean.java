package br.com.donazo.donazione.utils;

import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class UtilBean {

	/**
	 * Recupera, do escopo da requisição, o valor do parâmetro com nome informado.
	 *
	 * @param parametro nome do parâmetro que guarda o valor que se deseja recuperar
	 * @return
	 */
	public static String obterValor(final String parametro) {
		if (parametro == null) {
			return null;
		} else {
			final FacesContext currentInstance = FacesContext.getCurrentInstance();
			return currentInstance.getExternalContext().getRequestParameterMap().get(parametro);
		}
	}

	public static void adicionarValor(final String parametro, final String valor) {
		if ((parametro != null) && (valor != null)) {
			final FacesContext currentInstance = FacesContext.getCurrentInstance();
			currentInstance.getExternalContext().getRequestParameterMap().put(parametro, valor);
		}
	}

	public static void encerrarSessao() {
		final FacesContext currentInstance = FacesContext.getCurrentInstance();
		if (currentInstance != null) {
			final ExternalContext externalContext = currentInstance.getExternalContext();
			if (externalContext != null) {
				final Object session = externalContext.getSession(false);
				if ((session != null) && (session instanceof HttpSession)) {
					((HttpSession) session).invalidate();
				}
			}
		}
	}

	public static Object daSessao(final String chave) {
		if (chave == null) {
			return null;
		} else {
			final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			final Map<String, Object> sessao = externalContext.getSessionMap();
			return sessao.get(chave);
		}
	}

	public static void naSessao(final String chave, final Object valor) {
		final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		final Map<String, Object> sessao = externalContext.getSessionMap();
		// Preciosismo: remover antes de inserir
		sessao.remove(chave);
		sessao.put(chave, valor);
	}

	public static String obterLoginDaSessao() {
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		final ExternalContext e = facesContext.getExternalContext();
		if (e != null) {
			return e.getRemoteUser();
		} else {
			return null;
		}
	}

}
