/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.model.financeiro;

import br.com.construtora.model.*;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author agostinho
 */
@Entity
@Table(name = "pessoal")
@NamedQueries({@NamedQuery(name = "Pessoal.findAll", query = "SELECT p FROM Pessoal p"),            
    @NamedQuery(name = "Pessoal.findByFuncDtPaga", query = "SELECT p FROM Pessoal p, Despesas d WHERE p.funcionario = :funcionario and d.dtDesp = :dtDesp")})
public class Pessoal extends Despesas implements Serializable {

    @Basic(optional = false)
    @Column(name = "vr_norm")
    private BigDecimal vrNorm;
    @Basic(optional = false)
    @Column(name = "vr_inss")
    private BigDecimal vrInss;
    @Basic(optional = false)
    @Column(name = "vr_fgts")
    private BigDecimal vrFgts;
    @Column(name = "vr_tick_trans")
    private BigDecimal vrTickTrans;
    @Column(name = "desc_tick_trans")
    private BigDecimal descTickTrans;
    @Column(name = "vr_tick_alim")
    private BigDecimal vrTickAlim;
    @Column(name = "desc_tick_alim")
    private BigDecimal descTickAlim;
    @Column(name = "vr_assi_medi")
    private BigDecimal vrAssiMedi;
    @Column(name = "desc_assi_medi")
    private BigDecimal descAssiMedi;
    @Column(name = "desc_cont_sindi")
    private BigDecimal descContSindi;
    @Column(name = "desc_vr_inss")
    private BigDecimal descVrInss;
    @Basic(optional = false)
    @Column(name = "vr_liqu")
    private BigDecimal vrLiqu;
    @Column(name = "vr_tota_pago")
    private BigDecimal vrTotaPago;
    @JoinColumn(name = "funcionario_Pessoa_idPessoa", referencedColumnName = "Pessoa_idPessoa")
    @OneToOne(optional = false)
    private Funcionario funcionario;

    public Pessoal() {
    }

    public Pessoal(BigDecimal vrNorm, BigDecimal vrInss, BigDecimal vrFgts, BigDecimal vrTickTrans, BigDecimal descTickTrans, BigDecimal vrTickAlim, BigDecimal descTickAlim, BigDecimal vrAssiMedi, BigDecimal descAssiMedi, BigDecimal descContSindi, BigDecimal descVrInss, BigDecimal vrLiqu, BigDecimal vrTotaPago, Funcionario funcionario) {
        this.vrNorm = vrNorm;
        this.vrInss = vrInss;
        this.vrFgts = vrFgts;
        this.vrTickTrans = vrTickTrans;
        this.descTickTrans = descTickTrans;
        this.vrTickAlim = vrTickAlim;
        this.descTickAlim = descTickAlim;
        this.vrAssiMedi = vrAssiMedi;
        this.descAssiMedi = descAssiMedi;
        this.descContSindi = descContSindi;
        this.descVrInss = descVrInss;
        this.vrLiqu = vrLiqu;
        this.vrTotaPago = vrTotaPago;
        this.funcionario = funcionario;
    }

    public BigDecimal getVrNorm() {
        return vrNorm;
    }

    public void setVrNorm(BigDecimal vrNorm) {
        this.vrNorm = vrNorm;
    }

    public BigDecimal getVrInss() {
        return vrInss;
    }

    public void setVrInss(BigDecimal vrInss) {
        this.vrInss = vrInss;
    }

    public BigDecimal getVrFgts() {
        return vrFgts;
    }

    public void setVrFgts(BigDecimal vrFgts) {
        this.vrFgts = vrFgts;
    }

    public BigDecimal getDescTickAlim() {
        return descTickAlim;
    }

    public void setDescTickAlim(BigDecimal descTickAlim) {
        this.descTickAlim = descTickAlim;
    }

    public BigDecimal getDescTickTrans() {
        return descTickTrans;
    }

    public void setDescTickTrans(BigDecimal descTickTrans) {
        this.descTickTrans = descTickTrans;
    }

    public BigDecimal getVrTickAlim() {
        return vrTickAlim;
    }

    public void setVrTickAlim(BigDecimal vrTickAlim) {
        this.vrTickAlim = vrTickAlim;
    }

    public BigDecimal getVrTickTrans() {
        return vrTickTrans;
    }

    public void setVrTickTrans(BigDecimal vrTickTrans) {
        this.vrTickTrans = vrTickTrans;
    }

    public BigDecimal getVrAssiMedi() {
        return vrAssiMedi;
    }

    public void setVrAssiMedi(BigDecimal vrAssiMedi) {
        this.vrAssiMedi = vrAssiMedi;
    }

    public BigDecimal getDescAssiMedi() {
        return descAssiMedi;
    }

    public void setDescAssiMedi(BigDecimal descAssiMedi) {
        this.descAssiMedi = descAssiMedi;
    }

    public BigDecimal getDescContSindi() {
        return descContSindi;
    }

    public void setDescContSindi(BigDecimal descContSindi) {
        this.descContSindi = descContSindi;
    }

    public BigDecimal getDescVrInss() {
        return descVrInss;
    }

    public void setDescVrInss(BigDecimal descVrInss) {
        this.descVrInss = descVrInss;
    }

    public BigDecimal getVrLiqu() {
        return vrLiqu;
    }

    public void setVrLiqu(BigDecimal vrLiqu) {
        this.vrLiqu = vrLiqu;
    }

    public BigDecimal getVrTotaPago() {
        return vrTotaPago;
    }

    public void setVrTotaPago(BigDecimal vrTotaPago) {
        this.vrTotaPago = vrTotaPago;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
