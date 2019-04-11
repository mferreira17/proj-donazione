package br.com.donazo.donazione.utils;

import javax.faces.application.FacesMessage;

public class MessagesUtil {

    public static void criarMensagemDeInformacao(String resumo, String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_INFO, resumo, detalhe);
    }

    public static void criarMensagemDeInformacao(String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_INFO, "Sucesso", detalhe);
    }

    public static void criarMensagemDeAviso(String resumo, String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_WARN, resumo, detalhe);
    }

    public static void criarMensagemDeAviso(String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_WARN, "Alerta", detalhe);
    }

    public static void criarMensagemDeErro(String resumo, String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_ERROR, resumo, detalhe);
    }

    public static void criarMensagemDeErro(String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_ERROR, "Erro", detalhe);
    }

    private static void criarMensagem(FacesMessage.Severity tipo, String resumo, String detalhe) {
    	FacesMessage fm = new FacesMessage(resumo, detalhe);
        fm.setSeverity(tipo);
        FacesContextProduces.getFacesContext().addMessage(null, fm);
    }

}
