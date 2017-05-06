<%-- 
    Document   : templateCadastro
    Created on : 13/05/2011, 17:32:48
    Author     : agostinho

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
        <rich:tabPanel id="tabPanelCadastroCliente" switchType="client">
            <rich:tab id="tabDadosPessoais" label="Dados Pessoais" styleClass="labelDefault">
                <h:panelGrid id="gridTipoPessoa" columns="4" style="margin-left: 500px;">
                    <h:outputLabel value="Tipo Pessoa:"/>
                    <rich:comboBox id="tipoPessoa" value="#{PessoaBean.tipoPessoa}" required="true" requiredMessage="Campo Necessário: Tipo Pessoa"
                                   enableManualInput="false" width="50">
                        <f:selectItems value="#{PessoaBean.listaTipoPessoa}"/>
                    </rich:comboBox>
                    <h:outputLabel value="Sexo: "/>
                    <rich:comboBox id="tipoSexo" value="#{ClientesBean.sexo}" required="true" requiredMessage="Campo Necessário: Sexo"
                                   enableManualInput="false" width="50">
                        <f:selectItems value="#{ClientesBean.listaTipoSexo}"/>
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
                        <h:inputText id="rgCliente" value="#{ClientesBean.rg}" size="6" maxlength="9" style="margin-left: 25px;"
                                     required="true" requiredMessage="Campo Necessário: RG">
                            <rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip">
                                <span  style="white-space:nowrap">
                                    Ex: 6.986.547
                                </span>
                            </rich:toolTip>
                        </h:inputText>
                        <h:outputLabel value="Orgão Emissor: " style="margin-left: 25px"/>
                        <h:inputText id="orgaoEmissorCliente" value="#{ClientesBean.orgaoEmissor}" size="7" maxlength="10"
                                     required="true" requiredMessage="Campo Necessário: Orgão Emissor"
                                     onkeyup="UpperCase(this)" onkeydown="UpperCase(this)">
                            <rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip">
                                <span  style="white-space:nowrap">
                                    Ex: SSP/PB
                                </span>
                            </rich:toolTip>
                        </h:inputText>
                        <h:outputLabel value="CPF: " style="margin-left: 30px"/>
                        <h:inputText id="cpfCliente" value="#{ClientesBean.cpf}" size="10" maxlength="14"
                                     required="true" requiredMessage="Campo Necessário: CPF"
                                     onkeyup="MascaraCPFCNPJ(this)" onkeydown="MascaraCPFCNPJ(this)">
                            <rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip">
                                <span  style="white-space:nowrap">
                                    Ex: 056.192.569-52
                                </span>
                            </rich:toolTip>
                        </h:inputText>
                    </h:panelGrid>
                </h:panelGrid>

                <h:panelGrid id="gridDataNascimento" columns="2">
                    <h:outputLabel value="Data Nascimento: " style="margin-left: 30px"/>
                    <rich:calendar id="calendarDataNascimento" enableManualInput="true" locale="pt_BR"
                                   inputSize="6" required="true" requiredMessage="Campo Necessário: Data Nascimento" showFooter="false"
                                   value="#{ClientesBean.dataNascimento}" datePattern="dd/MM/yyyy"
                                   oninputkeydown="MascaraDATA(this)" oninputkeyup="MascaraDATA(this)"/>
                </h:panelGrid>

                <rich:separator id="gridSeparator" height="3" width="711" style="margin-top: 20px; margin-bottom: 20px; margin-left: 35px;"/>

                <h:outputLabel id="outLabelContatos" value="Contatos"/>

                <BR/><BR/>

                <h:panelGrid id="gridTelefones">
                    <h:panelGrid id="gridResidencial" columns="4">
                        <h:outputLabel value="Residencial: " style="margin-left: 30px"/>
                        <h:inputText id="dddResidencialCliente" value="#{PessoaBean.dddFone1}" size="1" maxlength="2" style="margin-left: 25px;"/>
                        <h:outputLabel value="-" style="margin-left: 5px; margin-right: 5px;"/>
                        <h:inputText id="telefoneResidencialCliente" value="#{PessoaBean.fone1}" size="6" maxlength="9"
                                     onkeyup="MascaraTelefone(this)"/>
                    </h:panelGrid>
                    <h:panelGrid id="gridComercial" columns="4">
                        <h:outputLabel value="Comercial: " style="margin-left: 30px"/>
                        <h:inputText id="dddComercialCliente" value="#{PessoaBean.dddFone2}" size="1" maxlength="2" style="margin-left: 41px;"/>
                        <h:outputLabel value="-" style="margin-left: 5px; margin-right: 5px;"/>
                        <h:inputText id="telefoneComercialCliente" value="#{PessoaBean.fone2}" size="6" maxlength="9"
                                     onkeyup="MascaraTelefone(this)"/>
                    </h:panelGrid>
                    <h:panelGrid id="gridCelular" columns="4">
                        <h:outputLabel value="Celular: " style="margin-left: 30px"/>
                        <h:inputText id="dddCelularCliente" value="#{PessoaBean.dddFone3}" size="1" maxlength="2" style="margin-left: 56px;"/>
                        <h:outputLabel value="-" style="margin-left: 5px; margin-right: 5px;"/>
                        <h:inputText id="telefoneCelularCliente" value="#{PessoaBean.fone3}" size="6" maxlength="9"
                                     onkeyup="MascaraTelefone(this)"/>
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
