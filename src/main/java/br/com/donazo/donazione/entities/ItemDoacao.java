package br.com.donazo.donazione.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "item_doacao")
public class ItemDoacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Descricao não pode ser em branco")
    @Column(name = "descricao",nullable = false)
    private String descricao;

    @Column(name = "valor")
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "campanha", referencedColumnName = "id")
    private Campanha campanha;

    @ManyToOne
    @JoinColumn(name = "doacao", referencedColumnName = "id")
    private Doacao doacao;

    @ManyToOne
    @JoinColumn(name = "item_campanha", referencedColumnName = "id")
    private ItemCampanha itemCampanha;

    public ItemDoacao() {
    }

    public ItemDoacao(Integer id) {
        this.id = id;
    }

    public ItemDoacao(Integer id, String descricao, BigDecimal valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    public Doacao getDoacao() {
        return doacao;
    }

    public void setDoacao(Doacao doacao) {
        this.doacao = doacao;
    }

    public ItemCampanha getItemCampanha() {
        return itemCampanha;
    }

    public void setItemCampanha(ItemCampanha itemCampanha) {
        this.itemCampanha = itemCampanha;
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
        if (!(object instanceof ItemDoacao)) {
            return false;
        }
        ItemDoacao other = (ItemDoacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.org.centrocac.entidade.ItemDoacao[ id=" + id + " ]";
    }
    
}
