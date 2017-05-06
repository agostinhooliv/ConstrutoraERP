/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author agostinhooliv
 */
@Entity
@Table(name = "usuario")
@PrimaryKeyJoinColumn(name = "Pessoa_idPessoa")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario")
    , @NamedQuery(name = "Usuario.findByDeLogi", query = "SELECT u FROM Usuario u WHERE u.deLogi = :deLogi")
    , @NamedQuery(name = "Usuario.findByDeSenh", query = "SELECT u FROM Usuario u WHERE u.deSenh = :deSenh")
    , @NamedQuery(name = "Usuario.findBySgStat", query = "SELECT u FROM Usuario u WHERE u.sgStat = :sgStat")
    , @NamedQuery(name = "Usuario.findByStRese", query = "SELECT u FROM Usuario u WHERE u.stRese = :stRese")
    , @NamedQuery(name = "Usuario.findByDtUsuaAtua", query = "SELECT u FROM Usuario u WHERE u.dtUsuaAtua = :dtUsuaAtua")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @Column(name = "de_logi")
    private String deLogi;
    @Column(name = "de_senh")
    private String deSenh;
    @Column(name = "sg_stat")
    private String sgStat;
    @Column(name = "st_rese")
    private Character stRese;
    @Basic(optional = false)
    @Column(name = "dt_usua_atua")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUsuaAtua;
    @JoinColumn(name = "perfil_idPerfil", referencedColumnName = "idPerfil")
    @ManyToOne(optional = false)
    private Perfil perfilidPerfil;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String deLogi, Date dtUsuaAtua) {
        this.idUsuario = idUsuario;
        this.deLogi = deLogi;
        this.dtUsuaAtua = dtUsuaAtua;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDeLogi() {
        return deLogi;
    }

    public void setDeLogi(String deLogi) {
        this.deLogi = deLogi;
    }

    public String getDeSenh() {
        return deSenh;
    }

    public void setDeSenh(String deSenh) {
        this.deSenh = deSenh;
    }

    public String getSgStat() {
        return sgStat;
    }

    public void setSgStat(String sgStat) {
        this.sgStat = sgStat;
    }

    public Character getStRese() {
        return stRese;
    }

    public void setStRese(Character stRese) {
        this.stRese = stRese;
    }

    public Date getDtUsuaAtua() {
        return dtUsuaAtua;
    }

    public void setDtUsuaAtua(Date dtUsuaAtua) {
        this.dtUsuaAtua = dtUsuaAtua;
    }

    public Perfil getPerfilidPerfil() {
        return perfilidPerfil;
    }

    public void setPerfilidPerfil(Perfil perfilidPerfil) {
        this.perfilidPerfil = perfilidPerfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.construtora.model.Usuario[ idUsuario=" + idUsuario + " ]";
    }
    
}
