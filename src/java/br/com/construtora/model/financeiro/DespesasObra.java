/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.model.financeiro;

import br.com.construtora.model.Obra;
import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author agostinhooliv
 */
@Entity
@Table(name = "despesas_obra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DespesasObra.findAll", query = "SELECT d FROM DespesasObra d")
    , @NamedQuery(name = "DespesasObra.findByIdDespesasobra", query = "SELECT d FROM DespesasObra d WHERE d.idDespesasobra = :idDespesasobra")})
public class DespesasObra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDespesas_obra")
    private Integer idDespesasobra;
    @JoinColumn(name = "despesas_idDespesas", referencedColumnName = "idDespesas")
    @OneToOne(optional = false)
    private Despesas despesasidDespesas;
    @JoinColumn(name = "obra_idObra", referencedColumnName = "idObra")
    @OneToOne(optional = false)
    private Obra obraidObra;

    public DespesasObra() {
    }

    public DespesasObra(Integer idDespesasobra) {
        this.idDespesasobra = idDespesasobra;
    }

    public Integer getIdDespesasobra() {
        return idDespesasobra;
    }

    public void setIdDespesasobra(Integer idDespesasobra) {
        this.idDespesasobra = idDespesasobra;
    }

    public Despesas getDespesasidDespesas() {
        return despesasidDespesas;
    }

    public void setDespesasidDespesas(Despesas despesasidDespesas) {
        this.despesasidDespesas = despesasidDespesas;
    }

    public Obra getObraidObra() {
        return obraidObra;
    }

    public void setObraidObra(Obra obraidObra) {
        this.obraidObra = obraidObra;
    }
    
}
