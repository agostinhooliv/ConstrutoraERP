<%-- 
    Document   : templateFuncionarios
    Created on : 20/05/2011, 21:16:40
    Author     : HOME

--%>

<%@taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@taglib uri="http://richfaces.org/rich" prefix="rich"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <rich:tabPanel id="tabPanelCadastroFuncionario" switchType="client">
            <rich:tab id="tabDadosPessoais" label="Dados Pessoais" styleClass="labelDefault">
                <h:panelGrid id="gridTipoPessoa" columns="4" style="margin-left: 490px;">
                    <h:outputLabel value="Tipo Pessoa:"/>
                    <rich:comboBox id="tipoPessoa" value="#{PessoaBean.tipoPessoa}" required="true"
                                   requiredMessage="Campo Necessário: Tipo Pessoa"
                                   enableManualInput="false" width="50">
                        <f:selectItems value="#{PessoaBean.listaTipoPessoa}"/>
                    </rich:comboBox>
                    <h:outputLabel value="Sexo: " style="margin-left: 10px;"/>
                    <rich:comboBox id="tipoSexo" value="#{FuncionariosBean.sexo}" required="true" requiredMessage="Campo Necessário: Sexo"
                                   enableManualInput="false" width="50">
                        <f:selectItems value="#{FuncionariosBean.listaTipoSexo}"/>
                    </rich:comboBox>
                </h:panelGrid>
                <BR/>

                <h:panelGrid id="gridNomeRG_ORGAO_CPF" columns="1">
                    <h:panelGrid id="gridNome" columns="2">
                        <h:outputLabel value="Nome: " style="margin-left: 30px;"/>
                        <h:inputText id="nomeCliente" value="#{PessoaBean.nome}" size="69" maxlength="150" style="margin-left: 10px;"
                                     required="true" requiredMessage="Campo Necessário: Nome"
                                     onkeyup="UpperCase(this)" onkeydown="UpperCase(this)"/>
                    </h:panelGrid>
                    <h:panelGrid id="gridRG_CPF" columns="6">
                        <h:outputLabel value="RG: " style="margin-left: 30px"/>
                        <h:inputText id="rgCliente" value="#{FuncionariosBean.rg}" size="9" maxlength="9" style="margin-left: 25px;"
                                     required="true" requiredMessage="Campo Necessário: RG"
                                     onkeyup="MascaraRG(this)" onkeydown="MascaraRG(this)">
                            <rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip">
                                <span  style="white-space:nowrap">
                                    Ex: 6.986.547
                                </span>
                            </rich:toolTip>
                        </h:inputText>
                        <h:outputLabel value="Orgão Emissor: " style="margin-left: 25px"/>
                        <h:inputText id="orgaoEmissorCliente" value="#{FuncionariosBean.orgaoEmissor}" size="7" maxlength="10"
                                     required="true" requiredMessage="Campo Necessário: Orgão Emissor"
                                     onkeyup="UpperCase(this)" onkeydown="UpperCase(this)">
                            <rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip">
                                <span  style="white-space:nowrap">
                                    Ex: SSP/PB
                                </span>
                            </rich:toolTip>
                        </h:inputText>
                        <h:outputLabel value="CPF: " style="margin-left: 30px"/>
                        <h:inputText id="cpfCliente" value="#{FuncionariosBean.cpf}" size="15" maxlength="14"
                                     required="true" requiredMessage="Campo Necessário: CPF"
                                     onkeyup="MascaraCPFCNPJ(this)" onkeydown="MascaraCPFCNPJ(this)">
                            <rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip">
                                <span  style="white-space:nowrap">
                                    Sem ponto ou traço.<BR/>Ex: 056.192.569-52
                                </span>
                            </rich:toolTip>
                        </h:inputText>
                    </h:panelGrid>
                    <h:panelGrid id="gridDataNascimento" columns="6" style="margin-top: 10px;">
                        <h:outputLabel value="Data Nascimento: " style="margin-left: 30px"/>
                        <rich:calendar id="calendarDataNascimento" enableManualInput="true" locale="pt_BR"
                                       inputSize="10" required="true" requiredMessage="Campo Necessário: Data Nascimento" showFooter="false"
                                       value="#{FuncionariosBean.dataNascimento}" datePattern="dd/MM/yyyy"
                                       oninputkeydown="MascaraDATA(this)" oninputkeyup="MascaraDATA(this)"/>
                    </h:panelGrid>  
                </h:panelGrid>

                <rich:separator id="gridSeparatorVinculoTrabalho" height="3" width="711" style="margin-top: 20px; margin-bottom: 20px; margin-left: 35px;"/>
                <h:outputLabel id="outLabelVinculoTrabalho" value="Vínculo de Trabalho"/>

                <h:panelGrid id="gridSetor" columns="4" style="margin-left: 380px;">
                    <h:outputLabel value="Setor: " style="margin-left: 30px"/>
                    <rich:comboBox id="setor" value="#{FuncionariosBean.setor}" required="true" requiredMessage="Campo Necessário: Setor"
                                   enableManualInput="false" width="80">
                        <f:selectItems value="#{FuncionariosBean.listaSetor}"/>
                    </rich:comboBox>
                    <h:outputLabel value="Cargo: " style="margin-left: 25px"/>
                    <rich:comboBox id="cargo" value="#{FuncionariosBean.cargo}" required="true" requiredMessage="Campo Necessário: Cargo"
                                   enableManualInput="false" width="130">
                        <f:selectItems value="#{FuncionariosBean.listaCargo}"/>
                    </rich:comboBox>
                </h:panelGrid>

                <BR/><BR/>

                <h:panelGrid id="gridCartTrab_Pis_TituElei_DataVinc" columns="1">
                    <h:panelGrid id="gridCarteiraTrabalho" columns="4">
                        <h:outputLabel value="Carteira Trabalho: " style="margin-left: 30px;"/>
                        <h:inputText id="numeroCarteiraTrabalho" value="#{FuncionariosBean.numeroCarteiraTrabalho}" size="30" maxlength="30" style="margin-left: 10px;"
                                     required="true" requiredMessage="Campo Necessário: Carteira Trabalho"/>
                        <h:outputLabel value="PIS: " style="margin-left: 30px;"/>
                        <h:inputText id="numeroPis" value="#{FuncionariosBean.numeroPis}" size="30" maxlength="30" style="margin-left: 10px;"
                                     required="true" requiredMessage="Campo Necessário: PIS"/>   
                    </h:panelGrid>
                    <h:panelGrid id="gridPis_TituloEleitoral" columns="4">
                        <h:outputLabel value="Título: " style="margin-left: 30px;"/>
                        <h:inputText id="numeroTitulo" value="#{FuncionariosBean.numeroTituloEleitoral}" size="30" maxlength="30" style="margin-left: 10px;"
                                     required="true" requiredMessage="Campo Necessário: PIS"/>
                        <h:outputLabel value="Data Assinatura Carteira: " style="margin-left: 30px"/>
                        <rich:calendar id="calendarDataAssinaturaCarteira" enableManualInput="true" locale="pt_BR"
                                       inputSize="10" required="true" requiredMessage="Campo Necessário: Data Assinatura Carteira" showFooter="false"
                                       value="#{FuncionariosBean.dataVinculoEmpregaticio}" datePattern="dd/MM/yyyy"
                                       oninputkeydown="MascaraDATA(this)" oninputkeyup="MascaraDATA(this)"/>
                    </h:panelGrid>
                </h:panelGrid>              

                <BR/>
                <rich:separator id="gridSeparatorContatos" height="3" width="711" style="margin-top: 20px; margin-bottom: 20px; margin-left: 35px;"/>
                <h:outputLabel id="outLabelContatos" value="Contatos"/>

                <BR/><BR/>

                <h:panelGrid id="gridTelefones">
                    <h:panelGrid id="gridResidencial" columns="4">
                        <h:outputLabel value="Residencial: " style="margin-left: 30px"/>
                        <h:inputText id="dddResidencialCliente" value="#{PessoaBean.dddFone1}" size="2" maxlength="2" style="margin-left: 25px;"/>
                        <h:outputLabel value="-" style="margin-left: 5px; margin-right: 5px;"/>
                        <h:inputText id="telefoneResidencialCliente" value="#{PessoaBean.fone1}" size="11" maxlength="9"
                                     onkeyup="MascaraTelefone(this)">
                            <rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip">
                                <span  style="white-space:nowrap">
                                    Sem ponto ou traço.<BR/>Ex: 32221870
                                </span>
                            </rich:toolTip>
                        </h:inputText>
                    </h:panelGrid>
                    <h:panelGrid id="gridComercial" columns="4">
                        <h:outputLabel value="Comercial: " style="margin-left: 30px"/>
                        <h:inputText id="dddComercialCliente" value="#{PessoaBean.dddFone2}" size="2" maxlength="2" style="margin-left: 41px;"/>
                        <h:outputLabel value="-" style="margin-left: 5px; margin-right: 5px;"/>
                        <h:inputText id="telefoneComercialCliente" value="#{PessoaBean.fone2}" size="11" maxlength="9"
                                     onkeyup="MascaraTelefone(this)">
                            <rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip">
                                <span  style="white-space:nowrap">
                                    Sem ponto ou traço.<BR/>Ex: 32221870
                                </span>
                            </rich:toolTip>
                        </h:inputText>
                    </h:panelGrid>
                    <h:panelGrid id="gridCelular" columns="4">
                        <h:outputLabel value="Celular: " style="margin-left: 30px"/>
                        <h:inputText id="dddCelularCliente" value="#{PessoaBean.dddFone3}" size="2" maxlength="2" style="margin-left: 56px;"/>
                        <h:outputLabel value="-" style="margin-left: 5px; margin-right: 5px;"/>
                        <h:inputText id="telefoneCelularCliente" value="#{PessoaBean.fone3}" size="11" maxlength="10"
                                     onkeyup="MascaraTelefone2(this)">
                            <rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip">
                                <span  style="white-space:nowrap">
                                    Sem ponto ou traço.<BR/>Ex: 999380043
                                </span>
                            </rich:toolTip>
                        </h:inputText>
                    </h:panelGrid>
                </h:panelGrid>

                <BR/>
                <h:panelGrid id="gridEmailPessoa" columns="2" style="margin-left: 429px;">
                    <h:outputLabel value="email:"/>
                    <h:inputText id="emailCliente" value="#{PessoaBean.email}" size="40" maxlength="200"
                                 onkeyup="LowerCase(this)" onkeydown="LowerCase(this)">
                        <rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip">
                            <span  style="white-space:nowrap">
                                Ex: contatos@construtorapontoa.com.br
                            </span>
                        </rich:toolTip>
                    </h:inputText>
                </h:panelGrid>

            </rich:tab>
            <rich:tab label="Endereço" styleClass="labelDefault">
                <a4j:include viewId="/paginas/enderecos.jsp"/>
            </rich:tab>
        </rich:tabPanel>
    </body>
</html>
