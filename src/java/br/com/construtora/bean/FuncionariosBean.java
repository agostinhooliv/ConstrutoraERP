/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.bean;

import br.com.construtora.dao.EnderecoDAO;
import br.com.construtora.dao.FuncionarioDAO;
import br.com.construtora.model.Endereco;
import br.com.construtora.model.Funcionario;
import br.com.construtora.enums.CargoEnum;
import br.com.construtora.enums.SetorEnum;
import br.com.construtora.exceptions.CpfCadastradoException;
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
 * @author HOME
 */
public class FuncionariosBean {

    //Atributos da tela
    private Boolean verCadastroFuncionario = Boolean.FALSE;
    private Boolean verAlteracaoFuncionario = Boolean.FALSE;
    private Boolean verRemocaoFuncionario = Boolean.FALSE;
    private Boolean verListaFuncionario = Boolean.FALSE;
    private List<Funcionario> listaFuncionario;
    private List<SelectItem> listaTipoSexo;
    private List<SelectItem> listaSetor;
    private List<SelectItem> listaCargo;
    private SimpleDateFormat sdf;
    private Boolean habilitarBotaoAtualizar = Boolean.TRUE;
    private Boolean habilitarBotaoRemover = Boolean.TRUE;
    private String acao; //Acões da tela. ex.: Cadastro, Alteração, Exclusão e Lista
    private Boolean existeFuncionario;
    private List<SelectItem> listaCpfFuncionario;
    //Atributos de conexão com banco
    private EnderecoDAO enderecoDAO;
    private FuncionarioDAO funcionarioDAO;
    //Dados do funcionario
    private String rg;
    private String orgaoEmissor;
    private String cpf;
    private String numeroCarteiraTrabalho;
    private Date dataNascimento;
    private String numeroPis;
    private String numeroTituloEleitoral;
    private Date dataVinculoEmpregaticio;
    private Character sexo;
    private String setor;
    private String cargo;
    private Integer idPessoa;
    private Integer idEndereco;

    /**
     * Creates a new instance of FuncionariosBean
     */
    public FuncionariosBean() {

        enderecoDAO = new EnderecoDAO();
        funcionarioDAO = new FuncionarioDAO();

        //Preenche a lista de sexo
        setListaTipoSexo(new ArrayList<SelectItem>());
        getListaTipoSexo().add(new SelectItem("F"));
        getListaTipoSexo().add(new SelectItem("M"));

        //Seta a lista de Funcionarios e Lista d CpfFuncionários
        setListaCpfFuncionario(new ArrayList<SelectItem>());
        setListaFuncionario(new ArrayList<Funcionario>());

        //Preenche a lista de setores
        setListaSetor(new ArrayList<SelectItem>());
        for (SetorEnum setores : SetorEnum.values()) {
            getListaSetor().add(new SelectItem(setores));
        }

        //Preenche a lista de cargos
        setListaCargo(new ArrayList<SelectItem>());
        for (CargoEnum cargos : CargoEnum.values()) {
            getListaCargo().add(new SelectItem(cargos));
        }
    }

    public void acoesPagina(ActionEvent ev) {

        acao = (String) FacesUtil.getActionAttribute(ev, "acao");

        if (acao.equals("cadastro")) {
            setVerCadastroFuncionario(Boolean.TRUE);
            setVerAlteracaoFuncionario(Boolean.FALSE);
            setVerRemocaoFuncionario(Boolean.FALSE);
            setVerListaFuncionario(Boolean.FALSE);
        } else if (acao.equals("alteracao")) {

            getListaCpfFuncionario().clear();

            for (Funcionario cpfFuncionarios : funcionarioDAO.findAll()) {
                getListaCpfFuncionario().add(new SelectItem(cpfFuncionarios.getCpf()));
            }

            setVerCadastroFuncionario(Boolean.FALSE);
            setVerAlteracaoFuncionario(Boolean.TRUE);
            setVerRemocaoFuncionario(Boolean.FALSE);
            setVerListaFuncionario(Boolean.FALSE);
        } else if (acao.equals("remocao")) {

            getListaCpfFuncionario().clear();

            for (Funcionario cpfFuncionarios : funcionarioDAO.findAll()) {
                getListaCpfFuncionario().add(new SelectItem(cpfFuncionarios.getCpf()));
            }

            setVerCadastroFuncionario(Boolean.FALSE);
            setVerAlteracaoFuncionario(Boolean.FALSE);
            setVerRemocaoFuncionario(Boolean.TRUE);
            setVerListaFuncionario(Boolean.FALSE);
        } else if (acao.equals("lista")) {

            getListaFuncionario().clear();

            for (Funcionario funcionarios : funcionarioDAO.findAll()) {
                getListaFuncionario().add(funcionarios);
            }

            setVerCadastroFuncionario(Boolean.FALSE);
            setVerAlteracaoFuncionario(Boolean.FALSE);
            setVerRemocaoFuncionario(Boolean.FALSE);
            setVerListaFuncionario(Boolean.TRUE);
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

            Funcionario funcionario = new Funcionario();
            funcionario.setIdTpPess(pessoaBean.getTipoPessoa());
            funcionario.setNmPess(pessoaBean.getNome());
            funcionario.setSgStat('A');

            if ((this.getRg() != null) && (this.getOrgaoEmissor() != null)) {
                funcionario.setRg(StringUtil.retiraCaracteres(this.getRg()));
                funcionario.setDeOrgRg(this.getOrgaoEmissor());
            }

            funcionario.setCpf(StringUtil.retiraCaracteres(cpf));

            funcionario.setNrCartTrab(this.numeroCarteiraTrabalho);
            funcionario.setNrPis(this.numeroPis);
            funcionario.setNrTituElei(this.numeroTituloEleitoral);
            funcionario.setIdTpPess(pessoaBean.getTipoPessoa());
            funcionario.setIdSexo(this.sexo);
            funcionario.setDeSetor(this.setor);
            funcionario.setDeCargo(this.cargo);

            if (pessoaBean.getDddFone1() != 0
                    && pessoaBean.getFone1() != null) {
                funcionario.setDddFone1(pessoaBean.getDddFone1());
                funcionario.setFone1(Integer.parseInt(StringUtil.retiraCaracteres(pessoaBean.getFone1())));
            }

            if (pessoaBean.getDddFone2() != 0
                    && pessoaBean.getFone2() != null) {
                funcionario.setDddFone2(pessoaBean.getDddFone2());
                funcionario.setFone2(Integer.parseInt(StringUtil.retiraCaracteres(pessoaBean.getFone2())));
            }
            if (pessoaBean.getDddFone3() != 0
                    && pessoaBean.getFone3() != null) {
                funcionario.setDddFone3(pessoaBean.getDddFone3());
                funcionario.setFone3(Integer.parseInt(StringUtil.retiraCaracteres(pessoaBean.getFone3())));
            }

            if (!pessoaBean.getEmail().isEmpty()
                    && pessoaBean.getEmail() != null) {
                funcionario.setEmaPess(pessoaBean.getEmail());
            }

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
            funcionario.setEnderecoIdEndereco(endereco);

            try {
                funcionario.setDtCada(sdf.parse(sdf.format(new Date())));
                funcionario.setDtNasc(sdf.parse(sdf.format(dataNascimento)));
                funcionario.setDtVincEmpr(sdf.parse(sdf.format(dataVinculoEmpregaticio)));
            } catch (ParseException ex) {
                Logger.getLogger(ClientesBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            enderecoDAO.salvar(endereco);
            funcionarioDAO.salvar(funcionario);
            mensagensBean.setMensagem("Funcionário cadastrado com sucesso..");

            System.out.println(pessoaBean.getTipoPessoa() + "\t" + pessoaBean.getNome() + "\t" + this.getRg() + "\t" + this.getOrgaoEmissor() + "\t" + this.getCpf() + "\t" + pessoaBean.getDddFone1() + "-" + pessoaBean.getFone1() + "\t" + pessoaBean.getDddFone2() + "-" + pessoaBean.getFone2() + "\t" + pessoaBean.getDddFone3() + "-" + pessoaBean.getFone3() + "\t" + pessoaBean.getEmail());
            System.out.println(enderecosBean.getTipoEndereco() + "\t" + enderecosBean.getTipoLogradouro() + "\t" + enderecosBean.getLogradouro() + "\t" + enderecosBean.getNumero() + "\t" + enderecosBean.getComplemento() + "\t" + enderecosBean.getBairro() + "\t" + enderecosBean.getCidade() + "\t" + enderecosBean.getEstado() + "\t" + enderecosBean.getCep());
        } catch (NumberFormatException ex) {
            Logger.getLogger(FuncionariosBean.class.getName()).log(Level.SEVERE, null, ex);
            mensagensBean.setMensagem("Erro interno do sistema. <BR/>Por favor entrar em contato com o suporte.");
        } catch (CpfCadastradoException ex) {
            Logger.getLogger(ClientesBean.class.getName()).log(Level.SEVERE, null, ex.getMessage());
            mensagensBean.setMensagem(ex.getMessage());
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
            Funcionario funcionario = new Funcionario();
            funcionario.setIdPessoa(idPessoa);

            //Dados Pessoais
            funcionario.setIdTpPess(pessoaBean.getTipoPessoa());
            funcionario.setIdSexo(this.sexo);
            funcionario.setNmPess(pessoaBean.getNome());
            funcionario.setSgStat('A');
            funcionario.setRg(this.getRg());
            funcionario.setDeOrgRg(this.getOrgaoEmissor());
            funcionario.setCpf(StringUtil.retiraCaracteres(this.getCpf()));

            try {
                funcionario.setDtNasc(sdf.parse(sdf.format(this.getDataNascimento())));
            } catch (ParseException ex) {
                Logger.getLogger(ClientesBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Dados de vínculo empregatício
            funcionario.setDeSetor(this.getSetor());
            funcionario.setDeCargo(this.getCargo());
            funcionario.setNrCartTrab(this.getNumeroCarteiraTrabalho());
            funcionario.setNrPis(this.getNumeroPis());
            funcionario.setNrTituElei(this.getNumeroTituloEleitoral());

            try {
                funcionario.setDtVincEmpr(sdf.parse(sdf.format(this.getDataVinculoEmpregaticio())));
            } catch (ParseException ex) {
                Logger.getLogger(ClientesBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Dados de contato
            if (pessoaBean.getDddFone1() != 0 && pessoaBean.getFone1() != null) {
                funcionario.setDddFone1(pessoaBean.getDddFone1());
                funcionario.setFone1(Integer.parseInt(StringUtil.retiraCaracteres(pessoaBean.getFone1())));
            }

            if (pessoaBean.getDddFone2() != 0 && pessoaBean.getFone2() != null) {
                funcionario.setDddFone2(pessoaBean.getDddFone2());
                funcionario.setFone2(Integer.parseInt(StringUtil.retiraCaracteres(pessoaBean.getFone2())));
            }
            if (pessoaBean.getDddFone3() != 0 && pessoaBean.getFone3() != null) {
                funcionario.setDddFone3(pessoaBean.getDddFone3());
                funcionario.setFone3(Integer.parseInt(StringUtil.retiraCaracteres(pessoaBean.getFone3())));
            }

            funcionario.setEmaPess(pessoaBean.getEmail());

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
            funcionario.setEnderecoIdEndereco(endereco);

            enderecoDAO.atualizar(endereco);
            funcionarioDAO.atualizar(funcionario);

            mensagensBean.setMensagem("Dados do funcionário alterados com sucesso..");
            System.out.println(pessoaBean.getTipoPessoa() + "\t" + pessoaBean.getNome() + "\t" + this.getRg() + "\t" + this.getOrgaoEmissor() + "\t" + this.getCpf() + "\t" + pessoaBean.getDddFone1() + "-" + pessoaBean.getFone1() + "\t" + pessoaBean.getDddFone2() + "-" + pessoaBean.getFone2() + "\t" + pessoaBean.getDddFone3() + "-" + pessoaBean.getFone3() + "\t" + pessoaBean.getEmail());

            System.out.println(enderecosBean.getTipoEndereco() + "\t" + enderecosBean.getTipoLogradouro() + "\t" + enderecosBean.getLogradouro() + "\t" + enderecosBean.getNumero() + "\t" + enderecosBean.getComplemento() + "\t" + enderecosBean.getBairro() + "\t" + enderecosBean.getCidade() + "\t" + enderecosBean.getEstado() + "\t" + enderecosBean.getCep());
        } catch (Exception ex) {
            Logger.getLogger(ClientesBean.class.getName()).log(Level.SEVERE, null, ex);
            mensagensBean.setMensagem("Erro interno do sistema. <BR/>Por favor entrar em contato com o suporte.");
        }
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

        try {

            Funcionario funcionario = funcionarioDAO.findByCPF(StringUtil.retiraCaracteres(this.getCpf()));

            //Se o cpf for de algum funcionario
            if (funcionario != null) {

                //Seta o idPessoa e idEndereço que poderá ser utilizado para atualização ou remoção
                idPessoa = funcionario.getIdPessoa();
                idEndereco = funcionario.getEnderecoIdEndereco().getIdEndereco();

                //Dados pessoais
                pessoaBean.setTipoPessoa(funcionario.getIdTpPess());
                this.setSexo(funcionario.getIdSexo());
                pessoaBean.setNome(funcionario.getNmPess());

                if ((funcionario.getRg() != null && funcionario.getDeOrgRg() != null)) {
                    this.setRg(funcionario.getRg());
                    this.setOrgaoEmissor(funcionario.getDeOrgRg());
                }
                this.setCpf(StringUtil.formataCPFCNPJ(funcionario.getCpf()).toString());
                this.setDataNascimento(funcionario.getDtNasc());

                //Dados de vínculo empregatício
                this.setSetor(funcionario.getDeSetor());
                this.setCargo(funcionario.getDeCargo());
                this.setNumeroCarteiraTrabalho(funcionario.getNrCartTrab());
                this.setNumeroPis(funcionario.getNrPis());
                this.setNumeroTituloEleitoral(funcionario.getNrTituElei());
                this.setDataVinculoEmpregaticio(funcionario.getDtVincEmpr());

                //Dados de contato
                if ((funcionario.getDddFone1() != null) && (funcionario.getFone1() != null)) {
                    pessoaBean.setDddFone1(funcionario.getDddFone1());
                    pessoaBean.setFone1(StringUtil.formataTelefone(funcionario.getFone1().toString()));
                }
                if ((funcionario.getDddFone2() != null) && (funcionario.getFone2() != null)) {
                    pessoaBean.setDddFone2(funcionario.getDddFone2());
                    pessoaBean.setFone2(StringUtil.formataTelefone(funcionario.getFone2().toString()));
                }
                if ((funcionario.getDddFone3() != null) && (funcionario.getFone3() != null)) {
                    pessoaBean.setDddFone3(funcionario.getDddFone3());
                    pessoaBean.setFone3(StringUtil.formataTelefone(funcionario.getFone3().toString()));
                }

                pessoaBean.setEmail(funcionario.getEmaPess());

                //Dados de Endereço
                enderecosBean.setTipoEndereco(funcionario.getEnderecoIdEndereco().getSgTpEnde());
                enderecosBean.setTipoLogradouro(funcionario.getEnderecoIdEndereco().getSgTpLogr());
                enderecosBean.setLogradouro(funcionario.getEnderecoIdEndereco().getNmLogr());
                enderecosBean.setNumero(funcionario.getEnderecoIdEndereco().getNrEnde());
                enderecosBean.setComplemento(funcionario.getEnderecoIdEndereco().getDeCompl());
                enderecosBean.setBairro(funcionario.getEnderecoIdEndereco().getNmBairr());
                enderecosBean.setCidade(funcionario.getEnderecoIdEndereco().getNmCida());
                enderecosBean.setEstado(funcionario.getEnderecoIdEndereco().getSgUf());

                if ((funcionario.getEnderecoIdEndereco().getCdCep() != null)) {
                    enderecosBean.setCep(StringUtil.formataCEP(funcionario.getEnderecoIdEndereco().getCdCep().toString()));
                }

                if (acao.equals("alteracao")) {
                    setVerCadastroFuncionario(Boolean.FALSE);
                    setVerAlteracaoFuncionario(Boolean.TRUE);
                    setVerRemocaoFuncionario(Boolean.FALSE);
                    setVerListaFuncionario(Boolean.FALSE);
                    setHabilitarBotaoAtualizar(Boolean.FALSE);
                    setHabilitarBotaoRemover(Boolean.TRUE);
                } else if (acao.equals("remocao")) {
                    setVerCadastroFuncionario(Boolean.FALSE);
                    setVerAlteracaoFuncionario(Boolean.FALSE);
                    setVerRemocaoFuncionario(Boolean.TRUE);
                    setVerListaFuncionario(Boolean.FALSE);
                    setHabilitarBotaoAtualizar(Boolean.TRUE);
                    setHabilitarBotaoRemover(Boolean.FALSE);
                }

                setExisteFuncionario(Boolean.TRUE);

            } else {
                mensagensBean.setMensagem("Funcionário não cadastrado! <BR/>Por favor verifique o cpf digitado.");
                setExisteFuncionario(Boolean.FALSE);

                setHabilitarBotaoAtualizar(Boolean.TRUE);
                setHabilitarBotaoRemover(Boolean.TRUE);

            }
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
            //Seta o cliente
            Funcionario funcionario = funcionarioDAO.findByCPF(StringUtil.retiraCaracteres(this.cpf));
            funcionario.setSgStat('E');

            //Remove o funcionário e após o endereço
            funcionarioDAO.apagar(funcionario);

            mensagensBean.setMensagem("Funcionário removido com sucesso!");

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
        pessoaBean.setNome(null);

        this.setRg(null);
        this.setOrgaoEmissor(null);
        this.setCpf(null);
        this.setNumeroCarteiraTrabalho(null);
        this.setNumeroPis(null);
        this.setNumeroTituloEleitoral(null);
        this.setDataNascimento(null);
        this.setDataVinculoEmpregaticio(null);
        this.setSetor(null);
        this.setCargo(null);

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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataVinculoEmpregaticio() {
        return dataVinculoEmpregaticio;
    }

    public void setDataVinculoEmpregaticio(Date dataVinculoEmpregaticio) {
        this.dataVinculoEmpregaticio = dataVinculoEmpregaticio;
    }

    public EnderecoDAO getEnderecoDAO() {
        return enderecoDAO;
    }

    public void setEnderecoDAO(EnderecoDAO enderecoDAO) {
        this.enderecoDAO = enderecoDAO;
    }

    public Boolean getExisteFuncionario() {
        return existeFuncionario;
    }

    public void setExisteFuncionario(Boolean existeFuncionario) {
        this.existeFuncionario = existeFuncionario;
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

    public List<SelectItem> getListaCpfFuncionario() {
        return listaCpfFuncionario;
    }

    public void setListaCpfFuncionario(List<SelectItem> listaCpfFuncionario) {
        this.listaCpfFuncionario = listaCpfFuncionario;
    }

    public List<Funcionario> getListaFuncionario() {
        return listaFuncionario;
    }

    public void setListaFuncionario(List<Funcionario> listaFuncionario) {
        this.listaFuncionario = listaFuncionario;
    }

    public List<SelectItem> getListaTipoSexo() {
        return listaTipoSexo;
    }

    public void setListaTipoSexo(List<SelectItem> listaTipoSexo) {
        this.listaTipoSexo = listaTipoSexo;
    }

    public List<SelectItem> getListaCargo() {
        return listaCargo;
    }

    public void setListaCargo(List<SelectItem> listaCargo) {
        this.listaCargo = listaCargo;
    }

    public List<SelectItem> getListaSetor() {
        return listaSetor;
    }

    public void setListaSetor(List<SelectItem> listaSetor) {
        this.listaSetor = listaSetor;
    }

    public String getNumeroCarteiraTrabalho() {
        return numeroCarteiraTrabalho;
    }

    public void setNumeroCarteiraTrabalho(String numeroCarteiraTrabalho) {
        this.numeroCarteiraTrabalho = numeroCarteiraTrabalho;
    }

    public String getNumeroPis() {
        return numeroPis;
    }

    public void setNumeroPis(String numeroPis) {
        this.numeroPis = numeroPis;
    }

    public String getNumeroTituloEleitoral() {
        return numeroTituloEleitoral;
    }

    public void setNumeroTituloEleitoral(String numeroTituloEleitoral) {
        this.numeroTituloEleitoral = numeroTituloEleitoral;
    }

    public String getOrgaoEmissor() {
        return orgaoEmissor;
    }

    public void setOrgaoEmissor(String orgaoEmissor) {
        this.orgaoEmissor = orgaoEmissor;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Boolean getVerAlteracaoFuncionario() {
        return verAlteracaoFuncionario;
    }

    public void setVerAlteracaoFuncionario(Boolean verAlteracaoFuncionario) {
        this.verAlteracaoFuncionario = verAlteracaoFuncionario;
    }

    public Boolean getVerCadastroFuncionario() {
        return verCadastroFuncionario;
    }

    public void setVerCadastroFuncionario(Boolean verCadastroFuncionario) {
        this.verCadastroFuncionario = verCadastroFuncionario;
    }

    public Boolean getVerListaFuncionario() {
        return verListaFuncionario;
    }

    public void setVerListaFuncionario(Boolean verListaFuncionario) {
        this.verListaFuncionario = verListaFuncionario;
    }

    public Boolean getVerRemocaoFuncionario() {
        return verRemocaoFuncionario;
    }

    public void setVerRemocaoFuncionario(Boolean verRemocaoFuncionario) {
        this.verRemocaoFuncionario = verRemocaoFuncionario;
    }
}
