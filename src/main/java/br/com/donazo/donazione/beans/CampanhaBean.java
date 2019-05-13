package br.com.donazo.donazione.beans;

import br.com.donazo.donazione.entities.Campanha;
import br.com.donazo.donazione.entities.ItemDoacao;
import br.com.donazo.donazione.entities.Meta;
import br.com.donazo.donazione.repositorios.CampanhaRepository;
import br.com.donazo.donazione.repositorios.MetaRepository;
import br.com.donazo.donazione.utils.MessagesUtil;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class CampanhaBean {

    private Campanha campanha;

    private Meta meta;

    private List<ItemDoacao> doacoes;

    private List<Campanha> campanhas;

    private CampanhaRepository campanhaRepository;

    private MetaRepository metaRepository;

    public CampanhaBean() {
    }

    @Autowired
    public CampanhaBean(CampanhaRepository repoCampanha, MetaRepository metaRepository, Campanha campanha, Meta meta) {
        this.campanha = campanha;
        this.meta = meta;
        this.campanhaRepository = repoCampanha;
        this.metaRepository = metaRepository;
    }

    public void init() {
        campanhas = (List<Campanha>) campanhaRepository.findAll();
    }

    public void salvar() {

        meta.setCampanha(campanha);
        campanha.addItemDoacao(doacoes);

        campanhaRepository.save(campanha);
        metaRepository.save(meta);
        
		MessagesUtil.criarMensagemDeInformacao("Campanha salva com sucesso");

        resetEntidades();
    }

    private void resetEntidades(){
        campanha = new Campanha();
        meta = new Meta();
        doacoes = new ArrayList<>();
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    public List<Campanha> getCampanhas() {
        return campanhas;
    }

    public void setCampanhas(List<Campanha> campanhas) {
        this.campanhas = campanhas;
    }

    public List<ItemDoacao> getDoacoes() {
        return doacoes;
    }

    public void setDoacoes(List<ItemDoacao> doacoes) {
        this.doacoes = doacoes;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
