package br.com.donazo.donazione.utils;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

@Named
public class DateUtil {

	@Produces
	@RequestScoped
	public Date getDataAtual() {
		return new Date();
	}
}