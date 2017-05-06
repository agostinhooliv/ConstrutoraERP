/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.bean;

import br.com.construtora.dao.EnderecoDAO;
import br.com.construtora.dao.FornecedorDAO;
import br.com.construtora.model.Endereco;
import br.com.construtora.model.Fornecedor;
import br.com.construtora.util.FacesUtil;
import br.com.construtora.util.StringUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class FornecedoresBean {

    //Atributos da tela
    private Boolean verCadastroFornecedor = Boolean.FALSE;
    private Boolean verAlteracaoFornecedor = Boolean.FALSE;
    private Boolean verRemocaoFornecedor = Boolean.FALSE;
    private Boolean verListaFornecedor = Boolean.FALSE;
    private List<Fornecedor> listaFornecedores;
    private SimpleDateFormat sdf;
    private Boolean habilitarBotaoAtualizar = Boolean.TRUE;
    private Boolean habilitarBotaoRemover = Boolean.TRUE;
    private String acao; //Acões da tela. ex.: Cadastro, Alteração, Exclusão e Lista
    private Boolean existeFornecedor;
    private List<SelectItem> listaCnpjFornecedores;
    //Atributos de conexão com banco
    private EnderecoDAO enderecoDAO;
    private FornecedorDAO fornecedorDAO;
    //Atributos do fornecedor
    private String cnpj;
    private String nomeFantasia;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private Integer idPessoa;
    private Integer idEndereco;

    /** Creates a new instance of FornecedoresBean */
    public FornecedoresBean() {
        enderecoDAO = new EnderecoDAO();
        fornecedorDAO = new FornecedorDAO();

        //Seta lista de cnpj e fornecedores
        setListaFornecedores(new ArrayList<Fornecedor>());
        setListaCnpjFornecedores(new ArrayList<SelectItem>());
    }

    public void acoesPagina(ActionEvent ev) {

        acao = (String) FacesUtil.getActionAttribute(ev, "acao");

        if (acao.equals("cadastro")) {

            setVerCadastroFornecedor(Boolean.TRUE);
            setVerAlteracaoFornecedor(Boolean.FALSE);
            setVerRemocaoFornecedor(Boolean.FALSE);
            setVerListaFornecedor(Boolean.FALSE);

        } else if (acao.equals("alteracao")) {

            getListaCnpjFornecedores().clear();

            for (Fornecedor cpfFornecedors : fornecedorDAO.findAll()) {
                getListaCnpjFornecedores().add(new SelectItem(cpfFornecedors.getCnpj()));
            }

            setVerCadastroFornecedor(Boolean.FALSE);
            setVerAlteracaoFornecedor(Boolean.TRUE);
            setVerRemocaoFornecedor(Boolean.FALSE);
            setVerListaFornecedor(Boolean.FALSE);

        } else if (acao.equals("remocao")) {

            getListaCnpjFornecedores().clear();

            for (Fornecedor fornecedor : fornecedorDAO.findAll()) {
                getListaCnpjFornecedores().add(new SelectItem(fornecedor.getCnpj()));
            }

            setVerCadastroFornecedor(Boolean.FALSE);
            setVerAlteracaoFornecedor(Boolean.FALSE);
            setVerRemocaoFornecedor(Boolean.TRUE);
            setVerListaFornecedor(Boolean.FALSE);

        } else if (acao.equals("lista")) {

            setListaFornecedores(new ArrayList<Fornecedor>());

            for (Fornecedor fornecedor : fornecedorDAO.findAll()) {
                getListaFornecedores().add(fornecedor);
            }

            setVerCadastroFornecedor(Boolean.FALSE);
            setVerAlteracaoFornecedor(Boolean.FALSE);
            setVerRemocaoFornecedor(Boolean.FALSE);
            setVerListaFornecedor(Boolean.TRUE);
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

        try {

            Fornecedor fornecedor = new Fornecedor();
            
            fornecedor.setNmPess(pessoaBean.getNome());
            fornecedor.setNmFant(nomeFantasia);
            fornecedor.setSgStat('A');
            fornecedor.setCnpj(StringUtil.retiraCaracteres(cnpj));
            fornecedor.setInscEsta(StringUtil.retiraCaracteres(inscricaoEstadual));
            fornecedor.setInscMuni(StringUtil.retiraCaracteres(inscricaoMunicipal));
            fornecedor.setIdTpPess('J');

            try {
                fornecedor.setDtCada(sdf.parse(sdf.format(new Date())));
            } catch (ParseException ex) {
                Logger.getLogger(ClientesBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (pessoaBean.getDddFone2() != 0 && pessoaBean.getFone2() != null) {
                fornecedor.setDddFone2(pessoaBean.getDddFone2());
                fornecedor.setFone2(Integer.parseInt(StringUtil.retiraCaracteres(pessoaBean.getFone2())));
            }

            fornecedor.setEmaPess(pessoaBean.getEmail());

            //Dados de Endereço
            ELResolver resolverEndereco = context.getApplication().getELResolver();
            EnderecosBean enderecosBean = (EnderecosBean) resolverEndereco.getValue(context.getELContext(), null, "EnderecosBean");

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
            fornecedor.setEnderecoIdEndereco(endereco);

            enderecoDAO.salvar(endereco);
            fornecedorDAO.salvar(fornecedor);
            mensagensBean.setMensagem("Fornecedor cadastrado com sucesso..");

            System.out.println(pessoaBean.getTipoPessoa() + "\t" + pessoaBean.getNome() + "\t" + this.getCnpj() + "\t" + pessoaBean.getDddFone1() + "-" + pessoaBean.getFone1() + "\t" + pessoaBean.getDddFone2() + "-" + pessoaBean.getFone2() + "\t" + pessoaBean.getDddFone3() + "-" + pessoaBean.getFone3() + "\t" + pessoaBean.getEmail());
            System.out.println(enderecosBean.getTipoEndereco() + "\t" + enderecosBean.getTipoLogradouro() + "\t" + enderecosBean.getLogradouro() + "\t" + enderecosBean.getNumero() + "\t" + enderecosBean.getComplemento() + "\t" + enderecosBean.getBairro() + "\t" + enderecosBean.getCidade() + "\t" + enderecosBean.getEstado() + "\t" + enderecosBean.getCep());
        } catch (Exception ex) {
            Logger.getLogger(ClientesBean.class.getName()).log(Level.SEVERE, null, ex);
            mensagensBean.setMensagem("Erro interno do sistema. <BR/>Por favor entrar em contato com o suporte.");
        }
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

        try {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setIdPessoa(idPessoa);

            //Dados Pessoais
            fornecedor.setIdTpPess(pessoaBean.getTipoPessoa());
            fornecedor.setNmPess(pessoaBean.getNome());
            fornecedor.setSgStat('A');
            fornecedor.setNmFant(this.getNomeFantasia());
            fornecedor.setCnpj(StringUtil.retiraCaracteres(this.cnpj));
            fornecedor.setInscEsta(StringUtil.retiraCaracteres(this.inscricaoEstadual));
            fornecedor.setInscMuni(StringUtil.retiraCaracteres(this.inscricaoMunicipal));
            fornecedor.setIdTpPess('J');

            if (pessoaBean.getDddFone2() != 0 && pessoaBean.getFone2() != null) {
                fornecedor.setDddFone2(pessoaBean.getDddFone2());
                fornecedor.setFone2(Integer.parseInt(StringUtil.retiraCaracteres(pessoaBean.getFone2())));
            }

            fornecedor.setEmaPess(pessoaBean.getEmail());

            //Dados de Endereço
            ELResolver resolverEndereco = context.getApplication().getELResolver();
            EnderecosBean enderecosBean = (EnderecosBean) resolverEndereco.getValue(context.getELContext(), null, "EnderecosBean");

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
            fornecedor.setEnderecoIdEndereco(endereco);

            enderecoDAO.atualizar(endereco);
            fornecedorDAO.atualizar(fornecedor);

            mensagensBean.setMensagem("Dados do fornecedor alterados com sucesso..");

            System.out.println(pessoaBean.getTipoPessoa() + "\t" + pessoaBean.getNome() + "\t" + this.getCnpj() + "\t" + pessoaBean.getDddFone1() + "-" + pessoaBean.getFone1() + "\t" + pessoaBean.getDddFone2() + "-" + pessoaBean.getFone2() + "\t" + pessoaBean.getDddFone3() + "-" + pessoaBean.getFone3() + "\t" + pessoaBean.getEmail());
            System.out.println(enderecosBean.getTipoEndereco() + "\t" + enderecosBean.getTipoLogradouro() + "\t" + enderecosBean.getLogradouro() + "\t" + enderecosBean.getNumero() + "\t" + enderecosBean.getComplemento() + "\t" + enderecosBean.getBairro() + "\t" + enderecosBean.getCidade() + "\t" + enderecosBean.getEstado() + "\t" + enderecosBean.getCep());
        } catch (Exception ex) {
            Logger.getLogger(ClientesBean.class.getName()).log(Level.SEVERE, null, ex);
            mensagensBean.setMensagem("Erro interno do sistema. <BR/>Por favor entrar em contato com o suporte.");
        }
    }

    public void remover(ActionEvent ev) {

        //Mesangens de sistema
        FacesContext context = FacesContext.getCurrentInstance();
        ELResolver resolverMensagens = context.getApplication().getELResolver();
        MensagensBean mensagensBean = (MensagensBean) resolverMensagens.getValue(context.getELContext(), null, "MensagensBean");


        try {
            //Seta o fornecedor
            Fornecedor fornecedor = fornecedorDAO.findByCNPJ(StringUtil.retiraCaracteres(this.cnpj));
            fornecedor.setSgStat('E');
            
            //Remove o fornecedor e após o endereço
            fornecedorDAO.apagar(fornecedor);

            mensagensBean.setMensagem("Fornecedor removido com sucesso!");

        } catch (Exception ex) {
            Logger.getLogger(ClientesBean.class.getName()).log(Level.SEVERE, null, ex);
            mensagensBean.setMensagem("Erro interno do sistema. <BR/>Por favor entrar em contato com o suporte.");
        }
    }

    public void pesquisarFornecedor(ActionEvent ev) {

        FacesContext context = FacesContext.getCurrentInstance();
        ELResolver resolverPessoa = context.getApplication().getELResolver();
        PessoaBean pessoaBean = (PessoaBean) resolverPessoa.getValue(context.getELContext(), null, "PessoaBean");

        ELResolver resolverEndereco = context.getApplication().getELResolver();
        EnderecosBean enderecosBean = (EnderecosBean) resolverEndereco.getValue(context.getELContext(), null, "EnderecosBean");

        //Mesangens de sistema
        ELResolver resolverMensagens = context.getApplication().getELResolver();
        MensagensBean mensagensBean = (MensagensBean) resolverMensagens.getValue(context.getELContext(), null, "MensagensBean");

        try {

            Fornecedor fornecedor = fornecedorDAO.findByCNPJ(StringUtil.retiraCaracteres(this.getCnpj()));

            //Se o cnpj for de algum fornecedor
            if (fornecedor != null) {

                //Seta o idPessoa e idEndereço que poderá ser utilizado para atualização ou remoção
                idPessoa = fornecedor.getIdPessoa();
                idEndereco = fornecedor.getEnderecoIdEndereco().getIdEndereco();

                //Dados pessoais
                pessoaBean.setTipoPessoa(fornecedor.getIdTpPess());
                pessoaBean.setNome(fornecedor.getNmPess());
                this.setNomeFantasia(fornecedor.getNmFant());
                this.setInscricaoEstadual(fornecedor.getInscEsta());
                this.setInscricaoMunicipal(fornecedor.getInscMuni());

                this.setCnpj(StringUtil.formataCPFCNPJ(fornecedor.getCnpj()).toString());

                //Dados de contato               
                if ((fornecedor.getDddFone2() != null) && (fornecedor.getFone2() != null)) {
                    pessoaBean.setDddFone2(fornecedor.getDddFone2());
                    pessoaBean.setFone2(StringUtil.formataTelefone(fornecedor.getFone2().toString()));
                }
                pessoaBean.setEmail(fornecedor.getEmaPess());

                //Dados de Endereço
                enderecosBean.setTipoEndereco(fornecedor.getEnderecoIdEndereco().getSgTpEnde());
                enderecosBean.setTipoLogradouro(fornecedor.getEnderecoIdEndereco().getSgTpLogr());
                enderecosBean.setLogradouro(fornecedor.getEnderecoIdEndereco().getNmLogr());
                enderecosBean.setNumero(fornecedor.getEnderecoIdEndereco().getNrEnde());
                enderecosBean.setComplemento(fornecedor.getEnderecoIdEndereco().getDeCompl());
                enderecosBean.setBairro(fornecedor.getEnderecoIdEndereco().getNmBairr());
                enderecosBean.setCidade(fornecedor.getEnderecoIdEndereco().getNmCida());
                enderecosBean.setEstado(fornecedor.getEnderecoIdEndereco().getSgUf());

                if ((fornecedor.getEnderecoIdEndereco().getCdCep() != null)) {
                    enderecosBean.setCep(StringUtil.formataCEP(fornecedor.getEnderecoIdEndereco().getCdCep().toString()));
                }

                if (acao.equals("alteracao")) {
                    setVerCadastroFornecedor(Boolean.FALSE);
                    setVerAlteracaoFornecedor(Boolean.TRUE);
                    setVerRemocaoFornecedor(Boolean.FALSE);
                    setVerListaFornecedor(Boolean.FALSE);
                    setHabilitarBotaoAtualizar(Boolean.FALSE);
                    setHabilitarBotaoRemover(Boolean.TRUE);
                } else if (acao.equals("remocao")) {
                    setVerCadastroFornecedor(Boolean.FALSE);
                    setVerAlteracaoFornecedor(Boolean.FALSE);
                    setVerRemocaoFornecedor(Boolean.TRUE);
                    setVerListaFornecedor(Boolean.FALSE);
                    setHabilitarBotaoAtualizar(Boolean.TRUE);
                    setHabilitarBotaoRemover(Boolean.FALSE);
                }

                setExisteFornecedor(Boolean.TRUE);

            } else {

                mensagensBean.setMensagem("Fornecedor não cadastrado! <BR/>Por favor verifique o cnpj digitado.");
                setExisteFornecedor(Boolean.FALSE);

                setHabilitarBotaoAtualizar(Boolean.TRUE);
                setHabilitarBotaoRemover(Boolean.TRUE);

            }
        } catch (Exception ex) {
            Logger.getLogger(ClientesBean.class.getName()).log(Level.SEVERE, null, ex);
            mensagensBean.setMensagem("Erro interno do sistema. <BR/>Por favor entrar em contato com o suporte.");
        }
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
        this.setNomeFantasia(null);
        this.setInscricaoEstadual(null);
        this.setInscricaoMunicipal(null);
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
        setHabilitarBotaoAtualizar(Boolean.TRUE);
        setHabilitarBotaoRemover(Boolean.TRUE);

        //Mesangens de sistema
        FacesContext context = FacesContext.getCurrentInstance();
        ELResolver resolverMensagens = context.getApplication().getELResolver();
        MensagensBean mensagensBean = (MensagensBean) resolverMensagens.getValue(context.getELContext(), null, "MensagensBean");
        mensagensBean.setMensagem("Processando requisição, por favor aguarde...");
    }

    public Boolean getExisteFornecedor() {
        return existeFornecedor;
    }

    public void setExisteFornecedor(Boolean existeFornecedor) {
        this.existeFornecedor = existeFornecedor;
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

    public List<SelectItem> getListaCnpjFornecedores() {
        return listaCnpjFornecedores;
    }

    public void setListaCnpjFornecedores(List<SelectItem> listaCnpjFornecedores) {
        this.listaCnpjFornecedores = listaCnpjFornecedores;
    }

    public List<Fornecedor> getListaFornecedores() {
        return listaFornecedores;
    }

    public void setListaFornecedores(List<Fornecedor> listaFornecedores) {
        this.listaFornecedores = listaFornecedores;
    }

    public Boolean getVerAlteracaoFornecedor() {
        return verAlteracaoFornecedor;
    }

    public void setVerAlteracaoFornecedor(Boolean verAlteracaoFornecedor) {
        this.verAlteracaoFornecedor = verAlteracaoFornecedor;
    }

    public Boolean getVerCadastroFornecedor() {
        return verCadastroFornecedor;
    }

    public void setVerCadastroFornecedor(Boolean verCadastroFornecedor) {
        this.verCadastroFornecedor = verCadastroFornecedor;
    }

    public Boolean getVerListaFornecedor() {
        return verListaFornecedor;
    }

    public void setVerListaFornecedor(Boolean verListaFornecedor) {
        this.verListaFornecedor = verListaFornecedor;
    }

    public Boolean getVerRemocaoFornecedor() {
        return verRemocaoFornecedor;
    }

    public void setVerRemocaoFornecedor(Boolean verRemocaoFornecedor) {
        this.verRemocaoFornecedor = verRemocaoFornecedor;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }
}
