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
@Table(name = "ferramentas")
@PrimaryKeyJoinColumn(name = "Bem_idBem")
@NamedQueries({@NamedQuery(name = "Ferramentas.findAll", query = "SELECT f FROM Ferramentas f"), @NamedQuery(name = "Ferramentas.findByDeFerr", query = "SELECT f FROM Ferramentas f WHERE f.deFerr = :deFerr"), @NamedQuery(name = "Ferramentas.findByDeEspe", query = "SELECT f FROM Ferramentas f WHERE f.deEspe = :deEspe")})
public class Ferramentas extends Bem implements Serializable {

    @Basic(optional = false)
    @Column(name = "de_ferr")
    private String deFerr;
    @Column(name = "de_espe")
    private String deEspe;

    public Ferramentas() {
    }

    public Ferramentas(String deFerr, String deEspe) {
        this.deFerr = deFerr;
        this.deEspe = deEspe;
    }

    public String getDeFerr() {
        return deFerr;
    }

    public void setDeFerr(String deFerr) {
        this.deFerr = deFerr;
    }

    public String getDeEspe() {
        return deEspe;
    }

    public void setDeEspe(String deEspe) {
        this.deEspe = deEspe;
    }
}
