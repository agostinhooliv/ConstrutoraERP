/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.bean;

import br.com.construtora.enums.EstadosEnum;
import br.com.construtora.enums.TipoEnderecoEnum;
import br.com.construtora.enums.TipoLogradouroEnum;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author agostinho
 */
public class EnderecosBean {

    private List<SelectItem> listaTipoEndereco;
    private List<SelectItem> listaTipoLogradouro;
    private List<SelectItem> listaEstados;
    private String tipoEndereco;
    private String tipoLogradouro;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String cep;
    private String estado;

    /** Creates a new instance of EnderecosBean */
    public EnderecosBean() {
        //Preenche a lista de tipos de endereço
        setListaTipoEndereco(new ArrayList<SelectItem>());
        for (TipoEnderecoEnum te : TipoEnderecoEnum.values()) {
            getListaTipoEndereco().add(new SelectItem(te));
        }

        //Preenche a lista de tipos de logradouro
        setListaTipoLogradouro(new ArrayList<SelectItem>());
        for (TipoLogradouroEnum tl : TipoLogradouroEnum.values()) {
            getListaTipoLogradouro().add(new SelectItem(tl));
        }

        //Preenche a lista de tipos de estados
        setListaEstados(new ArrayList<SelectItem>());
        for (EstadosEnum e : EstadosEnum.values()) {
            getListaEstados().add(new SelectItem(e));
        }
    }

    public List<SelectItem> getListaTipoEndereco() {
        return listaTipoEndereco;
    }

    public void setListaTipoEndereco(List<SelectItem> listaTipoEndereco) {
        this.listaTipoEndereco = listaTipoEndereco;
    }

    public List<SelectItem> getListaTipoLogradouro() {
        return listaTipoLogradouro;
    }

    public void setListaTipoLogradouro(List<SelectItem> listaTipoLogradouro) {
        this.listaTipoLogradouro = listaTipoLogradouro;
    }

    public List<SelectItem> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<SelectItem> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(String tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }
}
