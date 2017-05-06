/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.model;

import br.com.construtora.model.financeiro.Despesas;
import br.com.construtora.model.financeiro.DespesasObra;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author agostinho
 */
@Entity
@Table(name = "obra")
@NamedQueries({@NamedQuery(name = "Obra.findAll", query = "SELECT o FROM Obra o"), 
    @NamedQuery(name = "Obra.findByDeObra", query = "SELECT o FROM Obra o WHERE o.deObra = :deObra")})
public class Obra implements Serializable {    

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "obraidObra")
    private Collection<DespesasObra> despesasObraCollection;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idObra")
    private Integer idObra;   
    @Basic(optional = false)
    @Column(name = "de_obra")
    private String deObra;
    @Column(name = "sg_stat")
    private Character sgStat;
    @JoinColumn(name = "Endereco_idEndereco", referencedColumnName = "idEndereco")
    @OneToOne(optional = false)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Endereco enderecoIdEndereco;
//    @OneToOne (mappedBy = "idDespesas")
//    private Despesas despesas;
    

    public Obra() {
    }

    public Obra(Integer idObra) {
        this.idObra = idObra;
    }

    public Obra(Integer idObra, String deObra, Character sgStat, Endereco enderecoIdEndereco) {
        this.idObra = idObra;
        this.deObra = deObra;
        this.sgStat = sgStat;
        this.enderecoIdEndereco = enderecoIdEndereco;
    }

    public Integer getIdObra() {
        return idObra;
    }

    public void setIdObra(Integer idObra) {
        this.idObra = idObra;
    }

    public String getDeObra() {
        return deObra;
    }

    public void setDeObra(String deObra) {
        this.deObra = deObra;
    }

    public Character getSgStat() {
        return sgStat;
    }

    public void setSgStat(Character sgStat) {
        this.sgStat = sgStat;
    }

    public Endereco getEnderecoIdEndereco() {
        return enderecoIdEndereco;
    }

    public void setEnderecoIdEndereco(Endereco enderecoIdEndereco) {
        this.enderecoIdEndereco = enderecoIdEndereco;
    }

//    public Despesas getDespesas() {
//        return despesas;
//    }
//
//    public void setDespesas(Despesas despesas) {
//        this.despesas = despesas;
//    }
}
