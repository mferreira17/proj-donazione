package br.com.donazo.donazione.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Meta {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="valor_arrecadar")
	private BigDecimal valorArredacar;
	
	@Column(name="valor_arrecadado")
	private BigDecimal valorArrecadado;
	
	@Column(name="tem_expectativa_material")
	private boolean possuiExpectativaMaterial;
	
	@OneToOne
	@JoinColumn(name="id_campanha",referencedColumnName="id")
	private Campanha campanha;
	
	@OneToMany
	private List<Doacao> doacoes;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isPossuiExpectativaMaterial() {
		return possuiExpectativaMaterial;
	}

	public void setPossuiExpectativaMaterial(boolean possuiExpectativaMaterial) {
		this.possuiExpectativaMaterial = possuiExpectativaMaterial;
	}

	public BigDecimal getValorArrecadado() {
		return valorArrecadado;
	}

	public void setValorArrecadado(BigDecimal valorArrecadado) {
		this.valorArrecadado = valorArrecadado;
	}
	
	public void addDoacoes(Doacao doacao) {
		doacoes.add(doacao);
	}
	
	

}
