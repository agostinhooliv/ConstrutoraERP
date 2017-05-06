/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author agostinho
 */
public class PessoaBean {

    private List<SelectItem> listaTipoPessoa;
    private Character tipoPessoa;
    private String nome;
    private Integer dddFone1;
    private String fone1;
    private Integer dddFone2;
    private String fone2;
    private Integer dddFone3;
    private String fone3;
    private String email;

    /** Creates a new instance of PessoaBean */
    public PessoaBean() {
        //Preenche a lista de pessoa física
        setListaTipoPessoa(new ArrayList<SelectItem>());
        getListaTipoPessoa().add(new SelectItem("F"));
        getListaTipoPessoa().add(new SelectItem("J"));
    }

    public List<SelectItem> getListaTipoPessoa() {
        return listaTipoPessoa;
    }

    public void setListaTipoPessoa(List<SelectItem> listaTipoPessoa) {
        this.listaTipoPessoa = listaTipoPessoa;
    }

    public Character getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(Character tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Integer getDddFone1() {
        return dddFone1;
    }

    public void setDddFone1(Integer dddFone1) {
        this.dddFone1 = dddFone1;
    }

    public Integer getDddFone2() {
        return dddFone2;
    }

    public void setDddFone2(Integer dddFone2) {
        this.dddFone2 = dddFone2;
    }

    public Integer getDddFone3() {
        return dddFone3;
    }

    public void setDddFone3(Integer dddFone3) {
        this.dddFone3 = dddFone3;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone1() {
        return fone1;
    }

    public void setFone1(String fone1) {
        this.fone1 = fone1;
    }

    public String getFone2() {
        return fone2;
    }

    public void setFone2(String fone2) {
        this.fone2 = fone2;
    }

    public String getFone3() {
        return fone3;
    }

    public void setFone3(String fone3) {
        this.fone3 = fone3;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
