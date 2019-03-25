package br.com.donazo.donazione.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "item_campanha")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemCampanha.findAll", query = "SELECT i FROM ItemCampanha i")
    , @NamedQuery(name = "ItemCampanha.findById", query = "SELECT i FROM ItemCampanha i WHERE i.id = :id")
    , @NamedQuery(name = "ItemCampanha.findByNome", query = "SELECT i FROM ItemCampanha i WHERE i.nome = :nome")
    , @NamedQuery(name = "ItemCampanha.findByValor", query = "SELECT i FROM ItemCampanha i WHERE i.valor = :valor")})
public class ItemCampanha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nome")
    private String nome;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;
    @OneToMany(mappedBy = "itemCampanha")
    private List<ItemDoacao> itemDoacaoList;
    @JoinColumn(name = "campanha", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Campanha campanha;

    public ItemCampanha() {
    }

    public ItemCampanha(Integer id) {
        this.id = id;
    }

    public ItemCampanha(Integer id, String nome, BigDecimal valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @XmlTransient
    public List<ItemDoacao> getItemDoacaoList() {
        return itemDoacaoList;
    }

    public void setItemDoacaoList(List<ItemDoacao> itemDoacaoList) {
        this.itemDoacaoList = itemDoacaoList;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
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
        if (!(object instanceof ItemCampanha)) {
            return false;
        }
        ItemCampanha other = (ItemCampanha) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.org.centrocac.entidade.ItemCampanha[ id=" + id + " ]";
    }
    
}
