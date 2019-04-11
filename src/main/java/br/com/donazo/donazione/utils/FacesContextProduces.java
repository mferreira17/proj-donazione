package br.com.donazo.donazione.utils;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
public class FacesContextProduces {

	@Produces
	@RequestScoped
	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
}
