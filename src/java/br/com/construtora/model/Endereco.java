/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author agostinho
 */
@Entity
@Table(name = "endereco")
@NamedQueries({
    @NamedQuery(name = "Endereco.findAll", query = "SELECT e FROM Endereco e")
    ,
    @NamedQuery(name = "Endereco.findByIdEndereco", query = "SELECT e FROM Endereco e WHERE e.idEndereco = :idEndereco")
    , 
    @NamedQuery(name = "Endereco.findBySgTpEnde", query = "SELECT e FROM Endereco e WHERE e.sgTpEnde = :sgTpEnde")
    , 
    @NamedQuery(name = "Endereco.findBySgTpLogr", query = "SELECT e FROM Endereco e WHERE e.sgTpLogr = :sgTpLogr")
    , 
    @NamedQuery(name = "Endereco.findByNmLogr", query = "SELECT e FROM Endereco e WHERE e.nmLogr = :nmLogr")
    , 
    @NamedQuery(name = "Endereco.findByNrEnde", query = "SELECT e FROM Endereco e WHERE e.nrEnde = :nrEnde")
    ,
    @NamedQuery(name = "Endereco.findByNmBairr", query = "SELECT e FROM Endereco e WHERE e.nmBairr = :nmBairr")
    , 
    @NamedQuery(name = "Endereco.findByNmCida", query = "SELECT e FROM Endereco e WHERE e.nmCida = :nmCida")
    , 
    @NamedQuery(name = "Endereco.findByCdCep", query = "SELECT e FROM Endereco e WHERE e.cdCep = :cdCep")
    , 
    @NamedQuery(name = "Endereco.findBySgUf", query = "SELECT e FROM Endereco e WHERE e.sgUf = :sgUf")})
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEndereco")
    private Integer idEndereco;
    @Column(name = "sg_tp_ende")
    private String sgTpEnde;
    @Column(name = "sg_tp_logr")
    private String sgTpLogr;
    @Column(name = "nm_logr")
    private String nmLogr;
    @Column(name = "nr_ende")
    private String nrEnde;
    @Column(name = "nm_bairr")
    private String nmBairr;
    @Column(name = "de_compl")
    private String deCompl;
    @Column(name = "nm_cida")
    private String nmCida;
    @Column(name = "cd_cep")
    private Integer cdCep;
    @Column(name = "sg_uf")
    private String sgUf;

    public Endereco() {
    }

    public Endereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Endereco(String sgTpEnde, String sgTpLogr, String nmLogr, String nrEnde, String deCompl, String nmBairr, String nmCida, Integer cdCep, String sgUf) {
        this.sgTpEnde = sgTpEnde;
        this.sgTpLogr = sgTpLogr;
        this.nmLogr = nmLogr;
        this.nrEnde = nrEnde;
        this.deCompl = deCompl;
        this.nmBairr = nmBairr;
        this.nmCida = nmCida;
        this.cdCep = cdCep;
        this.sgUf = sgUf;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getSgTpEnde() {
        return sgTpEnde;
    }

    public void setSgTpEnde(String sgTpEnde) {
        this.sgTpEnde = sgTpEnde;
    }

    public String getSgTpLogr() {
        return sgTpLogr;
    }

    public void setSgTpLogr(String sgTpLogr) {
        this.sgTpLogr = sgTpLogr;
    }

    public String getNmLogr() {
        return nmLogr;
    }

    public void setNmLogr(String nmLogr) {
        this.nmLogr = nmLogr;
    }

    public String getNrEnde() {
        return nrEnde;
    }

    public void setNrEnde(String nrEnde) {
        this.nrEnde = nrEnde;
    }

    public String getDeCompl() {
        return deCompl;
    }

    public void setDeCompl(String deCompl) {
        this.deCompl = deCompl;
    }

    public String getNmBairr() {
        return nmBairr;
    }

    public void setNmBairr(String nmBairr) {
        this.nmBairr = nmBairr;
    }

    public String getNmCida() {
        return nmCida;
    }

    public void setNmCida(String nmCida) {
        this.nmCida = nmCida;
    }

    public Integer getCdCep() {
        return cdCep;
    }

    public void setCdCep(Integer cdCep) {
        this.cdCep = cdCep;
    }

    public String getSgUf() {
        return sgUf;
    }

    public void setSgUf(String sgUf) {
        this.sgUf = sgUf;
    }
}
