/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.bean;

import br.com.construtora.dao.EnderecoDAO;
import br.com.construtora.dao.ObraDAO;
import br.com.construtora.model.Endereco;
import br.com.construtora.model.Obra;
import br.com.construtora.util.FacesUtil;
import br.com.construtora.util.StringUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELResolver;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author agostinho
 */
public class ObrasBean {

    //Atributos da tela
    private Boolean verCadastroObras = Boolean.FALSE;
    private Boolean verAlteracaoObras = Boolean.FALSE;
    private Boolean verRemocaoObras = Boolean.FALSE;
    private Boolean verListaObras = Boolean.FALSE;
    private List<Obra> listaObras;
    private String acao; //Acões da tela. ex.: Cadastro, Alteração, Exclusão e Lista
    private Boolean existeObra;
    private Boolean habilitarBotaoAtualizar = Boolean.TRUE;
    private Boolean habilitarBotaoRemover = Boolean.TRUE;
    private List<SelectItem> listaNomeObras;
    //Atributos da obra
    private String nomeObra = null;
    //Atributos de conexão com banco
    private Integer idObra;
    private Integer idEndereco;
    private EnderecoDAO enderecoDAO;
    private ObraDAO obraDAO;

    /** Creates a new instance of ObrasBean */
    public ObrasBean() {
        enderecoDAO = new EnderecoDAO();
        obraDAO = new ObraDAO();

        setListaObras(new ArrayList<Obra>());
        setListaNomeObras(new ArrayList<SelectItem>());
    }

    public void acoesPagina(ActionEvent ev) {

        acao = (String) FacesUtil.getActionAttribute(ev, "acao");        

        if (acao.equals("cadastro")) {
            setVerCadastroObras(Boolean.TRUE);
            setVerAlteracaoObras(Boolean.FALSE);
            setVerRemocaoObras(Boolean.FALSE);
            setVerListaObras(Boolean.FALSE);
        } else if (acao.equals("alteracao")) {

            getListaNomeObras().clear();

            for (Obra obra : obraDAO.findAll()) {
                getListaNomeObras().add(new SelectItem(obra.getDeObra()));
            }

            setVerCadastroObras(Boolean.FALSE);
            setVerAlteracaoObras(Boolean.TRUE);
            setVerRemocaoObras(Boolean.FALSE);
            setVerListaObras(Boolean.FALSE);
        } else if (acao.equals("remocao")) {

            getListaNomeObras().clear();

            for (Obra obra : obraDAO.findAll()) {
                getListaNomeObras().add(new SelectItem(obra.getDeObra()));
            }

            setVerCadastroObras(Boolean.FALSE);
            setVerAlteracaoObras(Boolean.FALSE);
            setVerRemocaoObras(Boolean.TRUE);
            setVerListaObras(Boolean.FALSE);
            System.out.println(getVerRemocaoObras());
        } else if (acao.equals("lista")) {

            getListaObras().clear();

            for(Obra obra: obraDAO.findAll()){
                getListaObras().add(obra);
            }

            setVerCadastroObras(Boolean.FALSE);
            setVerAlteracaoObras(Boolean.FALSE);
            setVerRemocaoObras(Boolean.FALSE);
            setVerListaObras(Boolean.TRUE);
        }
    }

    public void cadastrar(ActionEvent ev) {

        FacesContext context = FacesContext.getCurrentInstance();

        //Mesangens de sistema
        ELResolver resolverMensagens = context.getApplication().getELResolver();
        MensagensBean mensagensBean = (MensagensBean) resolverMensagens.getValue(context.getELContext(), null, "MensagensBean");

        //Dados de Endereço
        ELResolver resolverEndereco = context.getApplication().getELResolver();
        EnderecosBean enderecosBean = (EnderecosBean) resolverEndereco.getValue(context.getELContext(), null, "EnderecosBean");

        try {

            Obra obra = new Obra();
            obra.setDeObra(nomeObra);
            obra.setSgStat('A');

            Endereco endereco = new Endereco();
            endereco.setSgTpEnde(enderecosBean.getTipoEndereco());
            endereco.setSgTpLogr(enderecosBean.getTipoLogradouro());
            endereco.setNmLogr(enderecosBean.getLogradouro());
            endereco.setNrEnde(enderecosBean.getNumero());
            endereco.setDeCompl(enderecosBean.getComplemento());
            endereco.setNmBairr(enderecosBean.getBairro());
            endereco.setNmCida(enderecosBean.getCidade());
            endereco.setSgUf(enderecosBean.getEstado());

            if ((enderecosBean.getCep() != null)) {
                endereco.setCdCep(Integer.parseInt(StringUtil.retiraCaracteres(enderecosBean.getCep())));
            }

            obra.setEnderecoIdEndereco(endereco);
            System.out.println(enderecosBean.getTipoEndereco());

            enderecoDAO.salvar(endereco);
            obraDAO.salvar(obra);

            mensagensBean.setMensagem("Obra cadastrada com sucesso..");

        } catch (Exception ex) {
            Logger.getLogger(ObrasBean.class.getName()).log(Level.SEVERE, null, ex);
            mensagensBean.setMensagem("Erro interno do sistema. <BR/>Por favor entrar em contato com o suporte.");
        }


    }

    public void alterar(ActionEvent ev) {

        FacesContext context = FacesContext.getCurrentInstance();

        //Mesangens de sistema
        ELResolver resolverMensagens = context.getApplication().getELResolver();
        MensagensBean mensagensBean = (MensagensBean) resolverMensagens.getValue(context.getELContext(), null, "MensagensBean");

        Obra obra = new Obra();
        obra.setIdObra(idObra);
        obra.setDeObra(nomeObra);
        obra.setSgStat('A');

        //Dados de Endereço
        ELResolver resolverEndereco = context.getApplication().getELResolver();
        EnderecosBean enderecosBean = (EnderecosBean) resolverEndereco.getValue(context.getELContext(), null, "EnderecosBean");

        try {
            Endereco endereco = new Endereco();
            endereco.setIdEndereco(idEndereco);
            endereco.setSgTpEnde(enderecosBean.getTipoEndereco());
            endereco.setSgTpLogr(enderecosBean.getTipoLogradouro());
            endereco.setNmLogr(enderecosBean.getLogradouro());
            endereco.setNrEnde(enderecosBean.getNumero());
            endereco.setDeCompl(enderecosBean.getComplemento());
            endereco.setNmBairr(enderecosBean.getBairro());
            endereco.setNmCida(enderecosBean.getCidade());
            endereco.setSgUf(enderecosBean.getEstado());
            if ((enderecosBean.getCep() != null)) {
                endereco.setCdCep(Integer.parseInt(StringUtil.retiraCaracteres(enderecosBean.getCep())));
            }

            obra.setEnderecoIdEndereco(endereco);

            enderecoDAO.atualizar(endereco);
            obraDAO.atualizar(obra);

            mensagensBean.setMensagem("Dados da obra alterados com sucesso..");

        } catch (Exception ex) {
            Logger.getLogger(ObrasBean.class.getName()).log(Level.SEVERE, null, ex);
            mensagensBean.setMensagem("Erro interno do sistema. <BR/>Por favor entrar em contato com o suporte.");
        }
    }

    public void remover(ActionEvent ev) {

        //Mesangens de sistema
        FacesContext context = FacesContext.getCurrentInstance();
        ELResolver resolverMensagens = context.getApplication().getELResolver();
        MensagensBean mensagensBean = (MensagensBean) resolverMensagens.getValue(context.getELContext(), null, "MensagensBean");


        try {
            //Seta a obra
            Obra obra = obraDAO.findByDeObra(nomeObra);
            obra.setSgStat('E');

            //Remove a obra
            obraDAO.apagar(obra);

            mensagensBean.setMensagem("Obra removida com sucesso!");

        } catch (Exception ex) {
            Logger.getLogger(ClientesBean.class.getName()).log(Level.SEVERE, null, ex);
            mensagensBean.setMensagem("Erro interno do sistema. <BR/>Por favor entrar em contato com o suporte.");
        }
    }

    public void pesquisarObras(ActionEvent ev) {

        FacesContext context = FacesContext.getCurrentInstance();
        ELResolver resolverEndereco = context.getApplication().getELResolver();
        EnderecosBean enderecosBean = (EnderecosBean) resolverEndereco.getValue(context.getELContext(), null, "EnderecosBean");

        //Mesangens de sistema
        ELResolver resolverMensagens = context.getApplication().getELResolver();
        MensagensBean mensagensBean = (MensagensBean) resolverMensagens.getValue(context.getELContext(), null, "MensagensBean");

        try {
            Obra obra = new ObraDAO().findByDeObra(nomeObra);

            //Se o nome for de alguma obra
            if (obra != null) {
                idObra = obra.getIdObra();
                idEndereco = obra.getEnderecoIdEndereco().getIdEndereco();
                //Dados da obra
                this.setNomeObra(obra.getDeObra());

                //Dados de Endereço
                enderecosBean.setTipoEndereco(obra.getEnderecoIdEndereco().getSgTpEnde());
                enderecosBean.setTipoLogradouro(obra.getEnderecoIdEndereco().getSgTpLogr());
                enderecosBean.setLogradouro(obra.getEnderecoIdEndereco().getNmLogr());
                enderecosBean.setNumero(obra.getEnderecoIdEndereco().getNrEnde());
                enderecosBean.setComplemento(obra.getEnderecoIdEndereco().getDeCompl());
                enderecosBean.setBairro(obra.getEnderecoIdEndereco().getNmBairr());
                enderecosBean.setCidade(obra.getEnderecoIdEndereco().getNmCida());
                enderecosBean.setEstado(obra.getEnderecoIdEndereco().getSgUf());

                if ((obra.getEnderecoIdEndereco().getCdCep() != null)) {
                    enderecosBean.setCep(StringUtil.formataCEP(obra.getEnderecoIdEndereco().getCdCep().toString()));
                }

                if (acao.equals("alteracao")) {
                    setVerCadastroObras(Boolean.FALSE);
                    setVerAlteracaoObras(Boolean.TRUE);
                    setVerRemocaoObras(Boolean.FALSE);
                    setVerListaObras(Boolean.FALSE);
                    setHabilitarBotaoAtualizar(Boolean.FALSE);
                    setHabilitarBotaoRemover(Boolean.TRUE);
                } else if (acao.equals("remocao")) {
                    setVerCadastroObras(Boolean.FALSE);
                    setVerAlteracaoObras(Boolean.FALSE);
                    setVerRemocaoObras(Boolean.TRUE);
                    setVerListaObras(Boolean.FALSE);
                    setHabilitarBotaoAtualizar(Boolean.TRUE);
                    setHabilitarBotaoRemover(Boolean.FALSE);
                }

                setExisteObra(Boolean.TRUE);
            } else {
                mensagensBean.setMensagem("Obra não cadastrada! <BR/>Por favor verifique o nome digitado.");
                setExisteObra(Boolean.FALSE);

                setHabilitarBotaoAtualizar(Boolean.TRUE);
                setHabilitarBotaoRemover(Boolean.TRUE);
            }
        } catch (Exception ex) {
            Logger.getLogger(ObrasBean.class.getName()).log(Level.SEVERE, null, ex);
            mensagensBean.setMensagem("Erro interno do sistema. <BR/>Por favor entrar em contato com o suporte.");
        }
    }

    public void fechar(ActionEvent ev) {
        FacesContext context = FacesContext.getCurrentInstance();

        ELResolver resolverEndereco = context.getApplication().getELResolver();
        EnderecosBean enderecosBean = (EnderecosBean) resolverEndereco.getValue(context.getELContext(), null, "EnderecosBean");

        //Reseta todos os campos, caso tenha sido feita uma pesquisa em um dos menus
        this.resetCampos(enderecosBean);
    }

    public void resetCampos(EnderecosBean enderecosBean) {
        //Dados da Obra
        this.setNomeObra(null);

        //Dados da tela
        getListaNomeObras().clear();
        getListaObras().clear();

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
        setHabilitarBotaoAtualizar(Boolean.TRUE);
        setHabilitarBotaoRemover(Boolean.TRUE);

        //Mesangens de sistema
        FacesContext context = FacesContext.getCurrentInstance();
        ELResolver resolverMensagens = context.getApplication().getELResolver();
        MensagensBean mensagensBean = (MensagensBean) resolverMensagens.getValue(context.getELContext(), null, "MensagensBean");
        mensagensBean.setMensagem("Processando requisição, por favor aguarde...");
    }

    public Boolean getExisteObra() {
        return existeObra;
    }

    public void setExisteObra(Boolean existeObra) {
        this.existeObra = existeObra;
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

    public List<Obra> getListaObras() {
        return listaObras;
    }

    public void setListaObras(List<Obra> listaObras) {
        this.listaObras = listaObras;
    }

    public List<SelectItem> getListaNomeObras() {
        return listaNomeObras;
    }

    public void setListaNomeObras(List<SelectItem> listaNomeObras) {
        this.listaNomeObras = listaNomeObras;
    }

    public String getNomeObra() {
        return nomeObra;
    }

    public void setNomeObra(String nomeObra) {
        this.nomeObra = nomeObra;
    }

    public Boolean getVerAlteracaoObras() {
        return verAlteracaoObras;
    }

    public void setVerAlteracaoObras(Boolean verAlteracaoObras) {
        this.verAlteracaoObras = verAlteracaoObras;
    }

    public Boolean getVerCadastroObras() {
        return verCadastroObras;
    }

    public void setVerCadastroObras(Boolean verCadastroObras) {
        this.verCadastroObras = verCadastroObras;
    }

    public Boolean getVerListaObras() {
        return verListaObras;
    }

    public void setVerListaObras(Boolean verListaObras) {
        this.verListaObras = verListaObras;
    }

    public Boolean getVerRemocaoObras() {
        return verRemocaoObras;
    }

    public void setVerRemocaoObras(Boolean verRemocaoObras) {
        this.verRemocaoObras = verRemocaoObras;
    }
}
