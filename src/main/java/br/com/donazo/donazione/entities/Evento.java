/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donazo.donazione.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "evento")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Evento.findAll", query = "SELECT a FROM Evento a"),
		@NamedQuery(name = "Evento.findById", query = "SELECT a FROM Evento a WHERE a.id = :id"),
		@NamedQuery(name = "Evento.findByNome", query = "SELECT a FROM Evento a WHERE a.nome = :nome"),
		@NamedQuery(name = "Evento.findByDescricao", query = "SELECT a FROM Evento a WHERE a.descricao = :descricao"),
		@NamedQuery(name = "Evento.findByInicio", query = "SELECT a FROM Evento a WHERE a.inicio = :inicio"),
		@NamedQuery(name = "Evento.findByFim", query = "SELECT a FROM Evento a WHERE a.fim = :fim"),
		@NamedQuery(name = "Evento.findByCadastro", query = "SELECT a FROM Evento a WHERE a.cadastro = :cadastro"),
		@NamedQuery(name = "Evento.findByCargaHoraria", query = "SELECT a FROM Evento a WHERE a.cargaHoraria = :cargaHoraria") })
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "nome")
	private String nome;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "descricao")
	private String descricao;
	@Basic(optional = false)
	@NotNull
	@Column(name = "inicio")
	@Temporal(TemporalType.TIMESTAMP)
	private Date inicio;
	@Basic(optional = false)
	@NotNull
	@Column(name = "fim")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fim;
	@Basic(optional = false)
	@NotNull
	@Column(name = "cadastro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date cadastro;
	@Basic(optional = false)
	@NotNull
	@Column(name = "cargaHoraria")
	private int cargaHoraria;

	@Basic(optional = false)
	@Column(name = "profissoesConvocadas")
	private String profissoesConvocadas;

	@OneToMany(mappedBy = "evento")
	private Set<Colaborador> colaboradorList = new HashSet<>();

	public Evento() {
	}

	public Evento(Integer id) {
		this.id = id;
	}

	public Evento(Integer id, String nome, String descricao, Date inicio, Date fim, Date cadastro, int cargaHoraria) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.inicio = inicio;
		this.fim = fim;
		this.cadastro = cadastro;
		this.cargaHoraria = cargaHoraria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public Date getCadastro() {
		return cadastro;
	}

	public void setCadastro(Date cadastro) {
		this.cadastro = cadastro;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public Set<Colaborador> getColaboradorList() {
		return colaboradorList;
	}

	public void setColaboradorList(Set<Colaborador> colaboradorList) {
		this.colaboradorList = colaboradorList;
	}

	public String getProfissoesConvocadas() {
		return profissoesConvocadas;
	}

	public void setProfissoesConvocadas(String profissoesConvocadas) {
		this.profissoesConvocadas = profissoesConvocadas;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Evento)) {
			return false;
		}
		Evento other = (Evento) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.org.centrocac.entidade.Evento[ id=" + id + " ]";
	}

}
