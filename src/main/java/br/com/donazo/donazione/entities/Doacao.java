package br.com.donazo.donazione.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "doacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Doacao.findAll", query = "SELECT d FROM Doacao d")
    , @NamedQuery(name = "Doacao.findById", query = "SELECT d FROM Doacao d WHERE d.id = :id")
    , @NamedQuery(name = "Doacao.findByCadastro", query = "SELECT d FROM Doacao d WHERE d.cadastro = :cadastro")
    , @NamedQuery(name = "Doacao.findByTotal", query = "SELECT d FROM Doacao d WHERE d.total = :total")})
public class Doacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cadastro;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private BigDecimal total;
    @JoinColumn(name = "colaborador", referencedColumnName = "id")
    @ManyToOne
    private Colaborador colaborador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doacao")
    private List<ItemDoacao> itemDoacaoList;

    public Doacao() {
    }

    public Doacao(Integer id) {
        this.id = id;
    }

    public Doacao(Integer id, Date cadastro, BigDecimal total) {
        this.id = id;
        this.cadastro = cadastro;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCadastro() {
        return cadastro;
    }

    public void setCadastro(Date cadastro) {
        this.cadastro = cadastro;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    @XmlTransient
    public List<ItemDoacao> getItemDoacaoList() {
        return itemDoacaoList;
    }

    public void setItemDoacaoList(List<ItemDoacao> itemDoacaoList) {
        this.itemDoacaoList = itemDoacaoList;
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
        if (!(object instanceof Doacao)) {
            return false;
        }
        Doacao other = (Doacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.org.centrocac.entidade.Doacao[ id=" + id + " ]";
    }
    
}
