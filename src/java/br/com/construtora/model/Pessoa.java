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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author agostinho
 */
@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p")
    , @NamedQuery(name = "Pessoa.findByIdPessoa", query = "SELECT p FROM Pessoa p WHERE p.idPessoa = :idPessoa")
    , @NamedQuery(name = "Pessoa.findByNmPess", query = "SELECT p FROM Pessoa p WHERE p.nmPess = :nmPess")
    , @NamedQuery(name = "Pessoa.findByDddFone1", query = "SELECT p FROM Pessoa p WHERE p.dddFone1 = :dddFone1")
    , @NamedQuery(name = "Pessoa.findByFone1", query = "SELECT p FROM Pessoa p WHERE p.fone1 = :fone1")
    , @NamedQuery(name = "Pessoa.findByDddFone2", query = "SELECT p FROM Pessoa p WHERE p.dddFone2 = :dddFone2")
    , @NamedQuery(name = "Pessoa.findByFone2", query = "SELECT p FROM Pessoa p WHERE p.fone2 = :fone2")
    , @NamedQuery(name = "Pessoa.findByDddFone3", query = "SELECT p FROM Pessoa p WHERE p.dddFone3 = :dddFone3")
    , @NamedQuery(name = "Pessoa.findByFone3", query = "SELECT p FROM Pessoa p WHERE p.fone3 = :fone3")
    , @NamedQuery(name = "Pessoa.findByIdTpPess", query = "SELECT p FROM Pessoa p WHERE p.idTpPess = :idTpPess")
    , @NamedQuery(name = "Pessoa.findByDtCada", query = "SELECT p FROM Pessoa p WHERE p.dtCada = :dtCada")
    , @NamedQuery(name = "Pessoa.findByEmaPess", query = "SELECT p FROM Pessoa p WHERE p.emaPess = :emaPess")})
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPessoa")
    private Integer idPessoa;
    @Basic(optional = false)
    @Column(name = "nm_pess")
    private String nmPess;
    @Basic(optional = false)
    @Column(name = "sg_stat")
    private Character sgStat;
    @Column(name = "ddd_fone1")
    private Integer dddFone1;
    @Column(name = "fone1")
    private Integer fone1;
    @Column(name = "ddd_fone2")
    private Integer dddFone2;
    @Column(name = "fone2")
    private Integer fone2;
    @Column(name = "ddd_fone3")
    private Integer dddFone3;
    @Column(name = "fone3")
    private Integer fone3;
    @Basic(optional = false)
    @Column(name = "id_tp_pess")
    private Character idTpPess;
    @Column(name = "dt_cada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCada;
    @Column(name = "ema_pess")
    private String emaPess;
    @JoinColumn(name = "Endereco_idEndereco", referencedColumnName = "idEndereco")
    @ManyToOne(optional = false)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Endereco enderecoIdEndereco;

    public Pessoa() {
    }

    public Pessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Pessoa(Integer idPessoa, String nmPess, char idTpPess, Date dtCada, Endereco enderecoIdEndereco) {
        this.idPessoa = idPessoa;
        this.nmPess = nmPess;
        this.idTpPess = idTpPess;
        this.dtCada = dtCada;
        this.enderecoIdEndereco = enderecoIdEndereco;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNmPess() {
        return nmPess;
    }

    public void setNmPess(String nmPess) {
        this.nmPess = nmPess;
    }

    public Integer getDddFone1() {
        return dddFone1;
    }

    public void setDddFone1(Integer dddFone1) {
        this.dddFone1 = dddFone1;
    }

    public Integer getFone1() {
        return fone1;
    }

    public void setFone1(Integer fone1) {
        this.fone1 = fone1;
    }

    public Integer getDddFone2() {
        return dddFone2;
    }

    public void setDddFone2(Integer dddFone2) {
        this.dddFone2 = dddFone2;
    }

    public Integer getFone2() {
        return fone2;
    }

    public void setFone2(Integer fone2) {
        this.fone2 = fone2;
    }

    public Integer getDddFone3() {
        return dddFone3;
    }

    public void setDddFone3(Integer dddFone3) {
        this.dddFone3 = dddFone3;
    }

    public Integer getFone3() {
        return fone3;
    }

    public void setFone3(Integer fone3) {
        this.fone3 = fone3;
    }

    public char getIdTpPess() {
        return idTpPess;
    }

    public void setIdTpPess(char idTpPess) {
        this.idTpPess = idTpPess;
    }

    public Date getDtCada() {
        return dtCada;
    }

    public void setDtCada(Date dtCada) {
        this.dtCada = dtCada;
    }

    public String getEmaPess() {
        return emaPess;
    }

    public void setEmaPess(String emaPess) {
        this.emaPess = emaPess;
    }

    public Endereco getEnderecoIdEndereco() {
        return enderecoIdEndereco;
    }

    public void setEnderecoIdEndereco(Endereco enderecoIdEndereco) {
        this.enderecoIdEndereco = enderecoIdEndereco;
    }

    public Character getSgStat() {
        return sgStat;
    }

    public void setSgStat(Character sgStat) {
        this.sgStat = sgStat;
    }
}
