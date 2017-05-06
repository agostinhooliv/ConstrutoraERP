/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.model.financeiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author agostinho
 */
@Entity
@Table(name = "despesas")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "Despesas.findAll", query = "SELECT d FROM Despesas d")
    ,
    @NamedQuery(name = "Despesas.findByIdDespesas", query = "SELECT d FROM Despesas d WHERE d.idDespesas = :idDespesas")
    ,
    @NamedQuery(name = "Despesas.findByDtDesp", query = "SELECT d FROM Despesas d WHERE d.dtDesp = :dtDesp")})
public class Despesas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDespesas")
    private Integer idDespesas;
    @Basic(optional = false)
    @Column(name = "dt_desp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtDesp;
    @Basic(optional = false)
    @Column(name = "sg_stat")
    private Character sgStat;
    @Basic(optional = true)
    @Column(name = "de_obs")
    private String deObs;
    

    public Despesas() {
    }

    public Despesas(Integer idDespesas) {
        this.idDespesas = idDespesas;
    }

    public Despesas(Integer idDespesas, Date dtDesp) {
        this.idDespesas = idDespesas;
        this.dtDesp = dtDesp;
    }

    public Integer getIdDespesas() {
        return idDespesas;
    }

    public void setIdDespesas(Integer idDespesas) {
        this.idDespesas = idDespesas;
    }

    public Date getDtDesp() {
        return dtDesp;
    }

    public void setDtDesp(Date dtDesp) {
        this.dtDesp = dtDesp;
    }

    public Character getSgStat() {
        return sgStat;
    }

    public void setSgStat(Character sgStat) {
        this.sgStat = sgStat;
    }

    public String getDeObs() {
        return deObs;
    }

    public void setDeObs(String deObs) {
        this.deObs = deObs;
    }
}
