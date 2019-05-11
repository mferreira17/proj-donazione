package br.com.donazo.donazione.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Component
public class Campanha implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Nome não pode ser vazio")
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @NotNull(message="Data de início não pode ser vazio")
    @Column(name = "data_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date cadastro;

    @Column(name = "habilitada", nullable = false)
    private boolean habilitada;

    @NotNull(message="Data de Término não pode ser vazio")
    @Column(name = "data_fim")
    @Temporal(TemporalType.DATE)
    private Date dataFim;

    /*@Basic(optional = false)
    @NotNull
    @Column(name = "meta")
    private BigDecimal meta;*/

    /*@Basic(optional = false)
    @NotNull
    @Column(name = "doacaoMinima")
    private BigDecimal doacaoMinima;*/

    /*@Basic(optional = false)
    @NotNull
    @Column(name = "arrecadado")
    private BigDecimal arrecadado;*/

    @OneToMany(mappedBy = "campanha")
    private Set<ItemDoacao> itemDoacaoList = new HashSet<>();

    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "campanha")
    private List<ItemCampanha> itemCampanhaList;*/

    public Campanha() {

    }
   
/*    public Campanha(Integer id) {
        this.id = id;
    }*/

/*
    public Campanha(Integer id, String nome, String descricao, Date cadastro, boolean habilitada, Date dataFim, BigDecimal meta, BigDecimal doacaoMinima, BigDecimal arrecadado) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.cadastro = cadastro;
        this.habilitada = habilitada;
        this.dataFim = dataFim;
    }
*/

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

    public Date getCadastro() {
        return cadastro;
    }

    public void setCadastro(Date cadastro) {
        this.cadastro = cadastro;
    }

    public boolean getHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

/*
    public BigDecimal getMeta() {
        return meta;
    }

    public void setMeta(BigDecimal meta) {
        this.meta = meta;
    }

    public BigDecimal getDoacaoMinima() {
        return doacaoMinima;
    }

    public void setDoacaoMinima(BigDecimal doacaoMinima) {
        this.doacaoMinima = doacaoMinima;
    }

    public BigDecimal getArrecadado() {
        return arrecadado;
    }

    public void setArrecadado(BigDecimal arrecadado) {
        this.arrecadado = arrecadado;
    }
*/

    public Set<ItemDoacao> getItemDoacaoList() {
        return itemDoacaoList;
    }

    public void setItemDoacaoList(Set<ItemDoacao> itemDoacaoList) {
        this.itemDoacaoList = itemDoacaoList;
    }

    /*@XmlTransient
    public List<ItemCampanha> getItemCampanhaList() {
        return itemCampanhaList;
    }

    public void setItemCampanhaList(List<ItemCampanha> itemCampanhaList) {
        this.itemCampanhaList = itemCampanhaList;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Campanha)) {
            return false;
        }
        Campanha other = (Campanha) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.org.centrocac.entidade.Campanha[ id=" + id + " ]";
    }

    public void addItemDoacao(ItemDoacao itemDoacao){
        this.itemDoacaoList.add(itemDoacao);
    }

    public void addItemDoacao(List<ItemDoacao> itensDoacao){
        this.itemDoacaoList.addAll(itensDoacao);
    }
    
}
