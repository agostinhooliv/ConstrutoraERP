/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author agostinho
 */
@Entity
@Table(name = "fornecedor")
@PrimaryKeyJoinColumn(name = "Pessoa_idPessoa")
@NamedQueries({@NamedQuery(name = "Fornecedor.findAll", query = "SELECT f FROM Fornecedor f"),
    @NamedQuery(name = "Fornecedor.findByCnpj", query = "SELECT f FROM Fornecedor f WHERE f.cnpj = :cnpj"),
    @NamedQuery(name = "Fornecedor.findByNmFant", query = "SELECT f FROM Fornecedor f WHERE f.nmFant = :nmFant"),
    @NamedQuery(name = "Fornecedor.findByInscEsta", query = "SELECT f FROM Fornecedor f WHERE f.inscEsta = :inscEsta"),
    @NamedQuery(name = "Fornecedor.findByInscMuni", query = "SELECT f FROM Fornecedor f WHERE f.inscMuni = :inscMuni")})
public class Fornecedor extends Pessoa implements Serializable {

    @Basic(optional = false)
    @Column(name = "cnpj", unique = true)
    private String cnpj;
    @Basic(optional = false)
    @Column(name = "nm_fant")
    private String nmFant;
    @Basic(optional = false)
    @Column(name = "insc_esta")
    private String inscEsta;
    @Basic(optional = false)
    @Column(name = "insc_muni")
    private String inscMuni;

    public Fornecedor() {
    }

    public Fornecedor(String nmFant, String inscEsta, String inscMuni) {
        this.nmFant = nmFant;
        this.inscEsta = inscEsta;
        this.inscMuni = inscMuni;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNmFant() {
        return nmFant;
    }

    public void setNmFant(String nmFant) {
        this.nmFant = nmFant;
    }

    public String getInscEsta() {
        return inscEsta;
    }

    public void setInscEsta(String inscEsta) {
        this.inscEsta = inscEsta;
    }

    public String getInscMuni() {
        return inscMuni;
    }

    public void setInscMuni(String inscMuni) {
        this.inscMuni = inscMuni;
    }
}
