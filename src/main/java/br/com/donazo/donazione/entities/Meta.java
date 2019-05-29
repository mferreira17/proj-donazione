package br.com.donazo.donazione.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Meta {

    public Meta() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "valor_arrecadar")
    private BigDecimal valorArredacar;

    @Column(name = "valor_arrecadado")
    private BigDecimal valorArrecadado;

    @Column(name = "tem_expectativa_material")
    private boolean possuiExpectativaMaterial;

    @OneToOne
    @JoinColumn(name = "id_campanha", referencedColumnName = "id")
    private Campanha campanha;

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

    public BigDecimal getValorArredacar() {
        return valorArredacar;
    }

    public void setValorArredacar(BigDecimal valorArredacar) {
        this.valorArredacar = valorArredacar;
    }

    public BigDecimal getValorArrecadado() {
        return valorArrecadado;
    }

    public void setValorArrecadado(BigDecimal valorArrecadado) {
        this.valorArrecadado = valorArrecadado;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }
}
