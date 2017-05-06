/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.bean.financeiro;

import br.com.construtora.bean.EnderecosBean;
import br.com.construtora.bean.MensagensBean;
import br.com.construtora.bean.ObrasBean;
import br.com.construtora.bean.PessoaBean;
import br.com.construtora.dao.ObraDAO;
import br.com.construtora.enums.TipoDespesasEnum;
import br.com.construtora.model.Obra;
import br.com.construtora.util.FacesUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.el.ELResolver;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author agostinhooliv
 */
public class DespesasBean {

    //Atributos da tela
    private Boolean verTelaCadastro = Boolean.FALSE;
    private Boolean verTelaAlteracao = Boolean.FALSE;
    private Boolean verTelaRemocao = Boolean.FALSE;
    private Boolean verTelaLista = Boolean.FALSE;
    private List<SelectItem> listaDespesas;
    private List<SelectItem> listaObras;
    private SimpleDateFormat sdf;
    private Boolean habilitarBotaoAtualizar = Boolean.TRUE;
    private Boolean habilitarBotaoRemover = Boolean.TRUE;
    private String acao; //Acões da tela. ex.: Cadastro, Alteração, Exclusão e Lista
    //Dados da Despesa
    private String tipoDespesa;
    private String obra;
    private String valorDespesa;
    private String observacao;
    private String saida;

    public DespesasBean() {
        setListaDespesas(new ArrayList<SelectItem>());
        for (TipoDespesasEnum despesas : TipoDespesasEnum.values()) {
            getListaDespesas().add(new SelectItem(despesas));
        }

        setListaObras(new ArrayList<SelectItem>());
        for (Obra obras : new ObraDAO().findAll()) {
            getListaObras().add(new SelectItem(obras.getDeObra()));
        }
    }

    public void acoesPagina(ActionEvent ev) {

        acao = (String) FacesUtil.getActionAttribute(ev, "acao");

        System.out.println(acao);

        if (acao.equals("cadastro")) {
            setVerTelaCadastro(Boolean.TRUE);
            setVerTelaAlteracao(Boolean.FALSE);
            setVerTelaRemocao(Boolean.FALSE);
            setVerTelaLista(Boolean.FALSE);
        } else if (acao.equals("alteracao")) {
            setVerTelaCadastro(Boolean.FALSE);
            setVerTelaAlteracao(Boolean.TRUE);
            setVerTelaRemocao(Boolean.FALSE);
            setVerTelaLista(Boolean.FALSE);
        } else if (acao.equals("remocao")) {
            setVerTelaCadastro(Boolean.FALSE);
            setVerTelaAlteracao(Boolean.FALSE);
            setVerTelaRemocao(Boolean.TRUE);
            setVerTelaLista(Boolean.FALSE);
        } else if (acao.equals("lista")) {
            setVerTelaCadastro(Boolean.FALSE);
            setVerTelaAlteracao(Boolean.FALSE);
            setVerTelaRemocao(Boolean.FALSE);
            setVerTelaLista(Boolean.TRUE);
        }

        FacesContext context = FacesContext.getCurrentInstance();

        ELResolver resolverPessoa = context.getApplication().getELResolver();
        PessoaBean pessoaBean = (PessoaBean) resolverPessoa.getValue(context.getELContext(), null, "PessoaBean");

        ELResolver resolverEndereco = context.getApplication().getELResolver();
        EnderecosBean enderecosBean = (EnderecosBean) resolverEndereco.getValue(context.getELContext(), null, "EnderecosBean");

        //Reseta todos os campos, caso tenha sido feita uma pesquisa em um dos menus
        this.resetCampos(pessoaBean, enderecosBean);
    }

    public void cadastrar(ActionEvent ev) {

        FacesContext context = FacesContext.getCurrentInstance();
        sdf = new SimpleDateFormat("dd/MM/yyyy hh:MM:ss");
        //Dados Pessoal
        ELResolver resolverPessoa = context.getApplication().getELResolver();
        PessoaBean pessoaBean = (PessoaBean) resolverPessoa.getValue(context.getELContext(), null, "PessoaBean");

        //Mesangens de sistema
        ELResolver resolverMensagens = context.getApplication().getELResolver();
        MensagensBean mensagensBean = (MensagensBean) resolverMensagens.getValue(context.getELContext(), null, "MensagensBean");

    }

    public void alterar(ActionEvent ev) {

        FacesContext context = FacesContext.getCurrentInstance();
        sdf = new SimpleDateFormat("dd/MM/yyyy hh:MM:ss");

        //Dados Pessoal
        ELResolver resolverPessoa = context.getApplication().getELResolver();
        PessoaBean pessoaBean = (PessoaBean) resolverPessoa.getValue(context.getELContext(), null, "PessoaBean");

        //Mesangens de sistema
        ELResolver resolverMensagens = context.getApplication().getELResolver();
        MensagensBean mensagensBean = (MensagensBean) resolverMensagens.getValue(context.getELContext(), null, "MensagensBean");

    }

    public void pesquisarFuncionarios(ActionEvent ev) {

        FacesContext context = FacesContext.getCurrentInstance();
        ELResolver resolverPessoa = context.getApplication().getELResolver();
        PessoaBean pessoaBean = (PessoaBean) resolverPessoa.getValue(context.getELContext(), null, "PessoaBean");

        ELResolver resolverEndereco = context.getApplication().getELResolver();
        EnderecosBean enderecosBean = (EnderecosBean) resolverEndereco.getValue(context.getELContext(), null, "EnderecosBean");

        //Mesangens de sistema
        ELResolver resolverMensagens = context.getApplication().getELResolver();
        MensagensBean mensagensBean = (MensagensBean) resolverMensagens.getValue(context.getELContext(), null, "MensagensBean");

    }
    
    public void adicionarDespesas(ActionEvent ev){
        saida += "Testando..<br/><br/>";
    }

    public void remover(ActionEvent ev) {

        //Mesangens de sistema
        FacesContext context = FacesContext.getCurrentInstance();
        ELResolver resolverMensagens = context.getApplication().getELResolver();
        MensagensBean mensagensBean = (MensagensBean) resolverMensagens.getValue(context.getELContext(), null, "MensagensBean");

    }

    public void fechar(ActionEvent ev) {
        FacesContext context = FacesContext.getCurrentInstance();

        ELResolver resolverPessoa = context.getApplication().getELResolver();
        PessoaBean pessoaBean = (PessoaBean) resolverPessoa.getValue(context.getELContext(), null, "PessoaBean");

        ELResolver resolverEndereco = context.getApplication().getELResolver();
        EnderecosBean enderecosBean = (EnderecosBean) resolverEndereco.getValue(context.getELContext(), null, "EnderecosBean");

        //Reseta todos os campos, caso tenha sido feita uma pesquisa em um dos menus
        this.resetCampos(pessoaBean, enderecosBean);
    }

    public void resetCampos(PessoaBean pessoaBean, EnderecosBean enderecosBean) {
        pessoaBean.setTipoPessoa(null);
        pessoaBean.setNome(null);

        pessoaBean.setDddFone1(null);
        pessoaBean.setFone1(null);
        pessoaBean.setDddFone2(null);
        pessoaBean.setFone2(null);
        pessoaBean.setDddFone3(null);
        pessoaBean.setFone3(null);
        pessoaBean.setEmail(null);

        //Dados de Endereço
        enderecosBean.setTipoEndereco(null);
        enderecosBean.setTipoLogradouro(null);
        enderecosBean.setLogradouro(null);
        enderecosBean.setNumero(null);
        enderecosBean.setComplemento(null);
        enderecosBean.setBairro(null);
        enderecosBean.setCidade(null);
        enderecosBean.setEstado(null);
        enderecosBean.setCep(null);

        //Seta os botões como invisíveis
//        setHabilitarBotaoAtualizar(Boolean.TRUE);
//        setHabilitarBotaoRemover(Boolean.TRUE);
        //Mesangens de sistema
        FacesContext context = FacesContext.getCurrentInstance();
        ELResolver resolverMensagens = context.getApplication().getELResolver();
        MensagensBean mensagensBean = (MensagensBean) resolverMensagens.getValue(context.getELContext(), null, "MensagensBean");
        mensagensBean.setMensagem("Processando requisição, por favor aguarde...");
    }

    public Boolean getVerTelaCadastro() {
        return verTelaCadastro;
    }

    public void setVerTelaCadastro(Boolean verTelaCadastro) {
        this.verTelaCadastro = verTelaCadastro;
    }

    public Boolean getVerTelaAlteracao() {
        return verTelaAlteracao;
    }

    public void setVerTelaAlteracao(Boolean verTelaAlteracao) {
        this.verTelaAlteracao = verTelaAlteracao;
    }

    public Boolean getVerTelaRemocao() {
        return verTelaRemocao;
    }

    public void setVerTelaRemocao(Boolean verTelaRemocao) {
        this.verTelaRemocao = verTelaRemocao;
    }

    public Boolean getVerTelaLista() {
        return verTelaLista;
    }

    public void setVerTelaLista(Boolean verTelaLista) {
        this.verTelaLista = verTelaLista;
    }

    public Boolean getHabilitarBotaoAtualizar() {
        return habilitarBotaoAtualizar;
    }

    public void setHabilitarBotaoAtualizar(Boolean habilitarBotaoAtualizar) {
        this.habilitarBotaoAtualizar = habilitarBotaoAtualizar;
    }

    public Boolean getHabilitarBotaoRemover() {
        return habilitarBotaoRemover;
    }

    public void setHabilitarBotaoRemover(Boolean habilitarBotaoRemover) {
        this.habilitarBotaoRemover = habilitarBotaoRemover;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public List<SelectItem> getListaDespesas() {
        return listaDespesas;
    }

    public void setListaDespesas(List<SelectItem> listaDespesas) {
        this.listaDespesas = listaDespesas;
    }

    public String getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(String tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    public List<SelectItem> getListaObras() {
        return listaObras;
    }

    public void setListaObras(List<SelectItem> listaObras) {
        this.listaObras = listaObras;
    }

    public String getObra() {
        return obra;
    }

    public void setObra(String obra) {
        this.obra = obra;
    }

    public String getValorDespesa() {
        return valorDespesa;
    }

    public void setValorDespesa(String valorDespesa) {
        this.valorDespesa = valorDespesa;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }
}
