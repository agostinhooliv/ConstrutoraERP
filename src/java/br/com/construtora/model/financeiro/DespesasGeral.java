/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.model.financeiro;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author agostinhooliv
 */
@Entity
@Table(name = "despesas_geral")
@PrimaryKeyJoinColumn(name = "Despesas_idDespesas")
@NamedQueries({
    @NamedQuery(name = "DespesasGeral.findAll", query = "SELECT d FROM DespesasGeral d")    
    , @NamedQuery(name = "DespesasGeral.findByTpDesp", query = "SELECT d FROM DespesasGeral d WHERE d.tpDesp = :tpDesp")
    , @NamedQuery(name = "DespesasGeral.findByDtVenc", query = "SELECT d FROM DespesasGeral d WHERE d.dtVenc = :dtVenc")})
public class DespesasGeral extends Despesas implements Serializable {

    private static final long serialVersionUID = 1L;
   
    @Basic(optional = false)
    @Column(name = "tp_desp")
    private String tpDesp;
    @Basic(optional = false)
    @Column(name = "vr_desp")
    private double vrDesp;
    @Column(name = "dt_venc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtVenc;

    public DespesasGeral() {
    }

    public DespesasGeral(String tpDesp, Double vrDesp) {
        this.tpDesp = tpDesp;
        this.vrDesp = vrDesp;
    }

    public String getTpDesp() {
        return tpDesp;
    }

    public void setTpDesp(String tpDesp) {
        this.tpDesp = tpDesp;
    }

    public double getVrDesp() {
        return vrDesp;
    }

    public void setVrDesp(double vrDesp) {
        this.vrDesp = vrDesp;
    }

    public Date getDtVenc() {
        return dtVenc;
    }

    public void setDtVenc(Date dtVenc) {
        this.dtVenc = dtVenc;
    }    
}
