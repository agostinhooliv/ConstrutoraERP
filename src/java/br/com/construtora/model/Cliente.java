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
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "Pessoa_idPessoa")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByCpf", query = "SELECT c FROM Cliente c WHERE c.cpf = :cpf"),
    @NamedQuery(name = "Cliente.findByRg", query = "SELECT c FROM Cliente c WHERE c.rg = :rg"),
    @NamedQuery(name = "Cliente.findByDeOrgRg", query = "SELECT c FROM Cliente c WHERE c.deOrgRg = :deOrgRg"),
    @NamedQuery(name = "Cliente.findByDtNasc", query = "SELECT c FROM Cliente c WHERE c.dtNasc = :dtNasc")})
public class Cliente extends Pessoa implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_sexo", unique = true)
    private Character idSexo;
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
    @Column(name = "dt_nasc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtNasc;

    public Cliente() {
    }

    public Cliente(Character idSexo, String cpf, String rg, String deOrgRg, Date dtNasc) {
        this.idSexo = idSexo;
        this.cpf = cpf;
        this.rg = rg;
        this.deOrgRg = deOrgRg;
        this.dtNasc = dtNasc;
    }

    public Character getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(Character idSexo) {
        this.idSexo = idSexo;
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

    public Date getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(Date dtNasc) {
        this.dtNasc = dtNasc;
    }
}
