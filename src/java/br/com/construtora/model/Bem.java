/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author agostinho
 */
@Entity
@Table(name = "bem")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({@NamedQuery(name = "Bem.findAll", query = "SELECT b FROM Bem b"), @NamedQuery(name = "Bem.findByIdBem", query = "SELECT b FROM Bem b WHERE b.idBem = :idBem"), @NamedQuery(name = "Bem.findByDeBem", query = "SELECT b FROM Bem b WHERE b.deBem = :deBem"), @NamedQuery(name = "Bem.findByDtComp", query = "SELECT b FROM Bem b WHERE b.dtComp = :dtComp"), @NamedQuery(name = "Bem.findByDtGara", query = "SELECT b FROM Bem b WHERE b.dtGara = :dtGara"), @NamedQuery(name = "Bem.findByVrBem", query = "SELECT b FROM Bem b WHERE b.vrBem = :vrBem")})
public class Bem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idBem")
    private Integer idBem;
    @Basic(optional = false)
    @Column(name = "de_bem")
    private String deBem;
    @Basic(optional = false)
    @Column(name = "dt_Comp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtComp;
    @Column(name = "dt_gara")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtGara;
    @Basic(optional = false)
    @Column(name = "vr_bem")
    private BigDecimal vrBem;   

    public Bem() {
    }

    public Bem(Integer idBem) {
        this.idBem = idBem;
    }

    public Bem(Integer idBem, String deBem, Date dtComp, BigDecimal vrBem) {
        this.idBem = idBem;
        this.deBem = deBem;
        this.dtComp = dtComp;
        this.vrBem = vrBem;
    }

    public Integer getIdBem() {
        return idBem;
    }

    public void setIdBem(Integer idBem) {
        this.idBem = idBem;
    }

    public String getDeBem() {
        return deBem;
    }

    public void setDeBem(String deBem) {
        this.deBem = deBem;
    }

    public Date getDtComp() {
        return dtComp;
    }

    public void setDtComp(Date dtComp) {
        this.dtComp = dtComp;
    }

    public Date getDtGara() {
        return dtGara;
    }

    public void setDtGara(Date dtGara) {
        this.dtGara = dtGara;
    }

    public BigDecimal getVrBem() {
        return vrBem;
    }

    public void setVrBem(BigDecimal vrBem) {
        this.vrBem = vrBem;
    }
}
