package br.com.donazo.donazione.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "item_doacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemDoacao.findAll", query = "SELECT i FROM ItemDoacao i")
    , @NamedQuery(name = "ItemDoacao.findById", query = "SELECT i FROM ItemDoacao i WHERE i.id = :id")
    , @NamedQuery(name = "ItemDoacao.findByDescricao", query = "SELECT i FROM ItemDoacao i WHERE i.descricao = :descricao")
    , @NamedQuery(name = "ItemDoacao.findByValor", query = "SELECT i FROM ItemDoacao i WHERE i.valor = :valor")})
public class ItemDoacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "descricao")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;
    @JoinColumn(name = "campanha", referencedColumnName = "id")
    @ManyToOne
    private Campanha campanha;
    @JoinColumn(name = "doacao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Doacao doacao;
    @JoinColumn(name = "item_campanha", referencedColumnName = "id")
    @ManyToOne
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
