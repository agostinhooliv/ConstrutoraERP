/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author agostinho
 */
@Entity
@Table(name = "funcionario")
@PrimaryKeyJoinColumn(name = "Pessoa_idPessoa")
@NamedQueries({
    @NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f"),
    @NamedQuery(name = "Funcionario.findByCpf", query = "SELECT f FROM Funcionario f WHERE f.cpf = :cpf"),
    @NamedQuery(name = "Funcionario.findByRg", query = "SELECT f FROM Funcionario f WHERE f.rg = :rg"),
    @NamedQuery(name = "Funcionario.findByDeOrgRg", query = "SELECT f FROM Funcionario f WHERE f.deOrgRg = :deOrgRg"),
    @NamedQuery(name = "Funcionario.findByNrCartTrab", query = "SELECT f FROM Funcionario f WHERE f.nrCartTrab = :nrCartTrab"),
    @NamedQuery(name = "Funcionario.findByDtNasc", query = "SELECT f FROM Funcionario f WHERE f.dtNasc = :dtNasc"),
    @NamedQuery(name = "Funcionario.findByNrPis", query = "SELECT f FROM Funcionario f WHERE f.nrPis = :nrPis"),
    @NamedQuery(name = "Funcionario.findByNrTituElei", query = "SELECT f FROM Funcionario f WHERE f.nrTituElei = :nrTituElei"),
    @NamedQuery(name = "Funcionario.findByDtVincEmpr", query = "SELECT f FROM Funcionario f WHERE f.dtVincEmpr = :dtVincEmpr"),
    @NamedQuery(name = "Funcionario.findByIdSexo", query = "SELECT f FROM Funcionario f WHERE f.idSexo = :idSexo"),
    @NamedQuery(name = "Funcionario.findByDeSetor", query = "SELECT f FROM Funcionario f WHERE f.deSetor = :deSetor"),
    @NamedQuery(name = "Funcionario.findByDeCargo", query = "SELECT f FROM Funcionario f WHERE f.deCargo = :deCargo")})

public class Funcionario extends Pessoa implements Serializable {   

    @Basic(optional = false)
    @Column(name = "cpf", unique = true)
    private String cpf;
    @Basic(optional = false)
    @Column(name = "rg")
    private String rg;
    @Basic(optional = false)
    @Column(name = "de_org_rg")
    private String deOrgRg;
    @Basic(optional = false)
    @Column(name = "nr_cart_trab")
    private String nrCartTrab;
    @Basic(optional = false)
    @Column(name = "dt_nasc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtNasc;
    @Basic(optional = false)
    @Column(name = "nr_pis")
    private String nrPis;
    @Basic(optional = false)
    @Column(name = "nr_titu_elei")
    private String nrTituElei;
    @Basic(optional = false)
    @Column(name = "dt_vinc_empr")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtVincEmpr;
    @Basic(optional = false)
    @Column(name = "id_sexo")
    private Character idSexo;
    @Basic(optional = false)
    @Column(name = "de_setor")
    private String deSetor;
    @Basic(optional = false)
    @Column(name = "de_cargo")
    private String deCargo;

    public Funcionario() {
    }

    public Funcionario(String cpf, String rg, String deOrgRg, String nrCartTrab, Date dtNasc, String nrPis, String nrTituElei, Date dtVincEmpr, char idSexo, String deSetor, String deCargo) {
        this.cpf = cpf;
        this.rg = rg;
        this.deOrgRg = deOrgRg;
        this.nrCartTrab = nrCartTrab;
        this.dtNasc = dtNasc;
        this.nrPis = nrPis;
        this.nrTituElei = nrTituElei;
        this.dtVincEmpr = dtVincEmpr;
        this.idSexo = idSexo;
        this.deSetor = deSetor;
        this.deCargo = deCargo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getDeOrgRg() {
        return deOrgRg;
    }

    public void setDeOrgRg(String deOrgRg) {
        this.deOrgRg = deOrgRg;
    }

    public String getNrCartTrab() {
        return nrCartTrab;
    }

    public void setNrCartTrab(String nrCartTrab) {
        this.nrCartTrab = nrCartTrab;
    }

    public Date getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(Date dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getNrPis() {
        return nrPis;
    }

    public void setNrPis(String nrPis) {
        this.nrPis = nrPis;
    }

    public String getNrTituElei() {
        return nrTituElei;
    }

    public void setNrTituElei(String nrTituElei) {
        this.nrTituElei = nrTituElei;
    }

    public Date getDtVincEmpr() {
        return dtVincEmpr;
    }

    public void setDtVincEmpr(Date dtVincEmpr) {
        this.dtVincEmpr = dtVincEmpr;
    }

    public char getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(char idSexo) {
        this.idSexo = idSexo;
    }

    public String getDeSetor() {
        return deSetor;
    }

    public void setDeSetor(String deSetor) {
        this.deSetor = deSetor;
    }

    public String getDeCargo() {
        return deCargo;
    }

    public void setDeCargo(String deCargo) {
        this.deCargo = deCargo;
    }
}
