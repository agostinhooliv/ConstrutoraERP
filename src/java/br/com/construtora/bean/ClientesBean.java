/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.bean;

import br.com.construtora.dao.ClienteDAO;
import br.com.construtora.dao.EnderecoDAO;
import br.com.construtora.model.Cliente;
import br.com.construtora.model.Endereco;
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
public class ClientesBean {

    //Atributos da tela
    private Boolean verCadastroCliente = Boolean.FALSE;
    private Boolean verAlteracaoCliente = Boolean.FALSE;
    private Boolean verRemocaoCliente = Boolean.FALSE;
    private Boolean verListaCliente = Boolean.FALSE;
    private List<Cliente> listaClientes;
    private SimpleDateFormat sdf;
    private Boolean habilitarBotaoAtualizar = Boolean.TRUE;
    private Boolean habilitarBotaoRemover = Boolean.TRUE;
    private String acao; //Acões da tela. ex.: Cadastro, Alteração, Exclusão e Lista
    private Boolean existeCliente;
    private List<SelectItem> listaCpfClientes;
    private List<SelectItem> listaTipoSexo;
    //Atributos de conexão com banco
    private EnderecoDAO enderecoDAO;
    private ClienteDAO clienteDAO;
    //Atributos do cliente
    private String rg;
    private String orgaoEmissor;
    private String cpf;
    private Date dataNascimento;
    private Integer idPessoa;
    private Integer idEndereco;
    private Character sexo;

    /** Creates a new instance of ClientesBean */
    public ClientesBean() {
        clienteDAO = new ClienteDAO();
        enderecoDAO = new EnderecoDAO();
        //Seta lista de cpf e clientes.
        setListaClientes(new ArrayList<Cliente>());
        setListaCpfClientes(new ArrayList<SelectItem>());

        //Preenche a lista de sexo
        setListaTipoSexo(new ArrayList<SelectItem>());
        getListaTipoSexo().add(new SelectItem("F"));
        getListaTipoSexo().add(new SelectItem("M"));
    }

    public void acoesPagina(ActionEvent ev) {

        acao = (String) FacesUtil.getActionAttribute(ev, "acao");

        if (acao.equals("cadastro")) {

            setVerCadastroCliente(Boolean.TRUE);
            setVerAlteracaoCliente(Boolean.FALSE);
            setVerRemocaoCliente(Boolean.FALSE);
            setVerListaCliente(Boolean.FALSE);

        } else if (acao.equals("alteracao")) {

            getListaCpfClientes().clear();

            for (Cliente cpfClientes : clienteDAO.findAll()) {
                getListaCpfClientes().add(new SelectItem(cpfClientes.getCpf()));
            }

            setVerCadastroCliente(Boolean.FALSE);
            setVerAlteracaoCliente(Boolean.TRUE);
            setVerRemocaoCliente(Boolean.FALSE);
            setVerListaCliente(Boolean.FALSE);

        } else if (acao.equals("remocao")) {

            getListaCpfClientes().clear();

            for (Cliente cliente : clienteDAO.findAll()) {
                getListaCpfClientes().add(new SelectItem(cliente.getCpf()));
            }

            setVerCadastroCliente(Boolean.FALSE);
            setVerAlteracaoCliente(Boolean.FALSE);
            setVerRemocaoCliente(Boolean.TRUE);
            setVerListaCliente(Boolean.FALSE);

        } else if (acao.equals("lista")) {

            setListaClientes(new ArrayList<Cliente>());

            for (Cliente clientes : clienteDAO.findAll()) {
                getListaClientes().add(clientes);
            }

            setVerCadastroCliente(Boolean.FALSE);
            setVerAlteracaoCliente(Boolean.FALSE);
            setVerRemocaoCliente(Boolean.FALSE);
            setVerListaCliente(Boolean.TRUE);
        }

        FacesContext context = FacesContext.getCurrentInstance();

        ELResolver resolverPessoa = context.getApplication().getELResolver();
        PessoaBean pessoaBean = (PessoaBean) resolverPessoa.getValue(context.getELContext(), null, "PessoaBean");

        ELResolver resolverEndereco = context.getApplication().getELResolver();
        EnderecosBean enderecosBean = (EnderecosBean) resolverEndereco.getValue(context.getELContext(), null, "EnderecosBean");

        //Reseta todos os campos, caso tenha sido feita uma pesquisa em algum dos menus
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
            Cliente cliente = new Cliente();
            cliente.setIdTpPess(pessoaBean.getTipoPessoa());
            cliente.setIdSexo(this.sexo);
            cliente.setNmPess(pessoaBean.getNome());

            if ((this.getRg() != null) && (this.getOrgaoEmissor() != null)) {
                cliente.setRg(StringUtil.retiraCaracteres(this.getRg()));
                cliente.setDeOrgRg(this.getOrgaoEmissor());
            }
            cliente.setCpf(StringUtil.retiraCaracteres(this.getCpf()));

            if (pessoaBean.getDddFone1() != 0 && pessoaBean.getFone1() != null) {
                cliente.setDddFone1(pessoaBean.getDddFone1());
                cliente.setFone1(Integer.parseInt(StringUtil.retiraCaracteres(pessoaBean.getFone1())));
            }

            if (pessoaBean.getDddFone2() != 0 && pessoaBean.getFone2() != null) {
                cliente.setDddFone2(pessoaBean.getDddFone2());
                cliente.setFone2(Integer.parseInt(StringUtil.retiraCaracteres(pessoaBean.getFone2())));
            }
            if (pessoaBean.getDddFone3() != 0 && pessoaBean.getFone3() != null) {
                cliente.setDddFone3(pessoaBean.getDddFone3());
                cliente.setFone3(Integer.parseInt(StringUtil.retiraCaracteres(pessoaBean.getFone3())));
            }
            cliente.setEmaPess(pessoaBean.getEmail());

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
            cliente.setEnderecoIdEndereco(endereco);

            try {
                cliente.setDtCada(sdf.parse(sdf.format(new Date())));
                cliente.setDtNasc(sdf.parse(sdf.format(dataNascimento)));
            } catch (ParseException ex) {
                Logger.getLogger(ClientesBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            enderecoDAO.salvar(endereco);
            clienteDAO.salvar(cliente);
            mensagensBean.setMensagem("Cliente cadastrado com sucesso..");

            System.out.println(pessoaBean.getTipoPessoa() + "\t" + pessoaBean.getNome() + "\t" + this.getRg() + "\t" + this.getOrgaoEmissor() + "\t" + this.getCpf() + "\t" + pessoaBean.getDddFone1() + "-" + pessoaBean.getFone1() + "\t" + pessoaBean.getDddFone2() + "-" + pessoaBean.getFone2() + "\t" + pessoaBean.getDddFone3() + "-" + pessoaBean.getFone3() + "\t" + pessoaBean.getEmail());
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
            Cliente cliente = new Cliente();
            cliente.setIdPessoa(idPessoa);

            //Dados Pessoais
            cliente.setIdTpPess(pessoaBean.getTipoPessoa());
            cliente.setNmPess(pessoaBean.getNome());
            cliente.setRg(this.getRg());
            cliente.setDeOrgRg(this.getOrgaoEmissor());
            cliente.setCpf(StringUtil.retiraCaracteres(this.getCpf()));

            try {
                cliente.setDtNasc(sdf.parse(sdf.format(this.dataNascimento)));
            } catch (ParseException ex) {
                Logger.getLogger(ClientesBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (pessoaBean.getDddFone1() != 0 && pessoaBean.getFone1() != null) {
                cliente.setDddFone1(pessoaBean.getDddFone1());
                cliente.setFone1(Integer.parseInt(StringUtil.retiraCaracteres(pessoaBean.getFone1())));
            }

            if (pessoaBean.getDddFone2() != 0 && pessoaBean.getFone2() != null) {
                cliente.setDddFone2(pessoaBean.getDddFone2());
                cliente.setFone2(Integer.parseInt(StringUtil.retiraCaracteres(pessoaBean.getFone2())));
            }
            if (pessoaBean.getDddFone3() != 0 && pessoaBean.getFone3() != null) {
                cliente.setDddFone3(pessoaBean.getDddFone3());
                cliente.setFone3(Integer.parseInt(StringUtil.retiraCaracteres(pessoaBean.getFone3())));
            }

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
            cliente.setEnderecoIdEndereco(endereco);

            enderecoDAO.atualizar(endereco);
            clienteDAO.atualizar(cliente);

            mensagensBean.setMensagem("Dados do cliente alterados com sucesso..");
            System.out.println(pessoaBean.getTipoPessoa() + "\t" + pessoaBean.getNome() + "\t" + this.getRg() + "\t" + this.getOrgaoEmissor() + "\t" + this.getCpf() + "\t" + pessoaBean.getDddFone1() + "-" + pessoaBean.getFone1() + "\t" + pessoaBean.getDddFone2() + "-" + pessoaBean.getFone2() + "\t" + pessoaBean.getDddFone3() + "-" + pessoaBean.getFone3() + "\t" + pessoaBean.getEmail());

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
            //Seta o cliente
            Cliente cliente = clienteDAO.findByCPF(StringUtil.retiraCaracteres(this.cpf));

            //Remove o cliente e após o endereço
            clienteDAO.apagar(cliente);

            mensagensBean.setMensagem("Cliente removido com sucesso!");

        } catch (Exception ex) {
            Logger.getLogger(ClientesBean.class.getName()).log(Level.SEVERE, null, ex);
            mensagensBean.setMensagem("Erro interno do sistema. <BR/>Por favor entrar em contato com o suporte.");
        }
    }

    public void pesquisarClientes(ActionEvent ev) {

        FacesContext context = FacesContext.getCurrentInstance();
        ELResolver resolverPessoa = context.getApplication().getELResolver();
        PessoaBean pessoaBean = (PessoaBean) resolverPessoa.getValue(context.getELContext(), null, "PessoaBean");

        ELResolver resolverEndereco = context.getApplication().getELResolver();
        EnderecosBean enderecosBean = (EnderecosBean) resolverEndereco.getValue(context.getELContext(), null, "EnderecosBean");

        //Mesangens de sistema
        ELResolver resolverMensagens = context.getApplication().getELResolver();
        MensagensBean mensagensBean = (MensagensBean) resolverMensagens.getValue(context.getELContext(), null, "MensagensBean");

        try {

            Cliente cliente = clienteDAO.findByCPF(StringUtil.retiraCaracteres(this.getCpf()));

            //Se o cpf for de algum cliente
            if (cliente != null) {

                //Seta o idPessoa e idEndereço que poderá ser utilizado para atualização ou remoção
                idPessoa = cliente.getIdPessoa();
                idEndereco = cliente.getEnderecoIdEndereco().getIdEndereco();

                //Dados pessoais
                pessoaBean.setTipoPessoa(cliente.getIdTpPess());
                pessoaBean.setNome(cliente.getNmPess());
                if ((cliente.getRg() != null && cliente.getDeOrgRg() != null)) {
                    this.setRg(cliente.getRg().toString());
                    this.setOrgaoEmissor(cliente.getDeOrgRg());
                }
                this.setCpf(StringUtil.formataCPFCNPJ(cliente.getCpf()).toString());

                //Dados de contato
                if ((cliente.getDddFone1() != null) && (cliente.getFone1() != null)) {
                    pessoaBean.setDddFone1(cliente.getDddFone1());
                    pessoaBean.setFone1(StringUtil.formataTelefone(cliente.getFone1().toString()));
                }
                if ((cliente.getDddFone2() != null) && (cliente.getFone2() != null)) {
                    pessoaBean.setDddFone2(cliente.getDddFone2());
                    pessoaBean.setFone2(StringUtil.formataTelefone(cliente.getFone2().toString()));
                }
                if ((cliente.getDddFone3() != null) && (cliente.getFone3() != null)) {
                    pessoaBean.setDddFone3(cliente.getDddFone3());
                    pessoaBean.setFone3(StringUtil.formataTelefone(cliente.getFone3().toString()));
                }
                pessoaBean.setEmail(cliente.getEmaPess());
                this.setDataNascimento(cliente.getDtNasc());

                //Dados de Endereço
                enderecosBean.setTipoEndereco(cliente.getEnderecoIdEndereco().getSgTpEnde());
                enderecosBean.setTipoLogradouro(cliente.getEnderecoIdEndereco().getSgTpLogr());
                enderecosBean.setLogradouro(cliente.getEnderecoIdEndereco().getNmLogr());
                enderecosBean.setNumero(cliente.getEnderecoIdEndereco().getNrEnde());
                enderecosBean.setComplemento(cliente.getEnderecoIdEndereco().getDeCompl());
                enderecosBean.setBairro(cliente.getEnderecoIdEndereco().getNmBairr());
                enderecosBean.setCidade(cliente.getEnderecoIdEndereco().getNmCida());
                enderecosBean.setEstado(cliente.getEnderecoIdEndereco().getSgUf());

                if ((cliente.getEnderecoIdEndereco().getCdCep() != null)) {
                    enderecosBean.setCep(StringUtil.formataCEP(cliente.getEnderecoIdEndereco().getCdCep().toString()));
                }

                if (acao.equals("alteracao")) {
                    setVerCadastroCliente(Boolean.FALSE);
                    setVerAlteracaoCliente(Boolean.TRUE);
                    setVerRemocaoCliente(Boolean.FALSE);
                    setVerListaCliente(Boolean.FALSE);
                    setHabilitarBotaoAtualizar(Boolean.FALSE);
                    setHabilitarBotaoRemover(Boolean.TRUE);
                } else if (acao.equals("remocao")) {
                    setVerCadastroCliente(Boolean.FALSE);
                    setVerAlteracaoCliente(Boolean.FALSE);
                    setVerRemocaoCliente(Boolean.TRUE);
                    setVerListaCliente(Boolean.FALSE);
                    setHabilitarBotaoAtualizar(Boolean.TRUE);
                    setHabilitarBotaoRemover(Boolean.FALSE);
                }

                setExisteCliente(Boolean.TRUE);

            } else {
                mensagensBean.setMensagem("Cliente não cadastrado! <BR/>Por favor verifique o cpf digitado.");
                setExisteCliente(Boolean.FALSE);

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
        pessoaBean.setNome(null);
        this.setRg(null);
        this.setOrgaoEmissor(null);
        this.setCpf(null);
        pessoaBean.setDddFone1(null);
        pessoaBean.setFone1(null);
        pessoaBean.setDddFone2(null);
        pessoaBean.setFone2(null);
        pessoaBean.setDddFone3(null);
        pessoaBean.setFone3(null);
        pessoaBean.setEmail(null);
        this.setDataNascimento(null);

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

    public Boolean getVerAlteracaoCliente() {
        return verAlteracaoCliente;
    }

    public void setVerAlteracaoCliente(Boolean verAlteracaoCliente) {
        this.verAlteracaoCliente = verAlteracaoCliente;
    }

    public Boolean getVerCadastroCliente() {
        return verCadastroCliente;
    }

    public void setVerCadastroCliente(Boolean verCadastroCliente) {
        this.verCadastroCliente = verCadastroCliente;
    }

    public Boolean getVerListaCliente() {
        return verListaCliente;
    }

    public void setVerListaCliente(Boolean verListaCliente) {
        this.verListaCliente = verListaCliente;
    }

    public Boolean getVerRemocaoCliente() {
        return verRemocaoCliente;
    }

    public void setVerRemocaoCliente(Boolean verRemocaoCliente) {
        this.verRemocaoCliente = verRemocaoCliente;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public Boolean getExisteCliente() {
        return existeCliente;
    }

    public void setExisteCliente(Boolean existeCliente) {
        this.existeCliente = existeCliente;
    }

    public List<SelectItem> getListaCpfClientes() {
        return listaCpfClientes;
    }

    public void setListaCpfClientes(List<SelectItem> listaCpfClientes) {
        this.listaCpfClientes = listaCpfClientes;
    }

    public List<SelectItem> getListaTipoSexo() {
        return listaTipoSexo;
    }

    public void setListaTipoSexo(List<SelectItem> listaTipoSexo) {
        this.listaTipoSexo = listaTipoSexo;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }
}
