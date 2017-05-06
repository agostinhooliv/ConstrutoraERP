<%-- 
    Document   : TemplateFornecedores
    Created on : 24/05/2011, 09:57:16
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
        <rich:tabPanel id="tabPanelCadastroFornecedor" switchType="client">
            <rich:tab id="tabDadosPessoais" label="Dados Pessoais" styleClass="labelDefault">
                <h:panelGrid columns="1">
                    <h:panelGrid columns="2" style="margin-left: 580px;">
                        <h:outputLabel value="CNPJ: "/>
                        <h:inputText id="cnpjFornecedor" value="#{FornecedoresBean.cnpj}" size="18" maxlength="18"
                                     required="true" requiredMessage="Campo Necessário: CPF"
                                     onkeyup="MascaraCPFCNPJ(this)" onkeydown="MascaraCPFCNPJ(this)">
                            <rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip">
                                <span  style="white-space:nowrap">
                                    Sem ponto ou traço.<BR/>Ex: 11986547000155
                                </span>
                            </rich:toolTip>
                        </h:inputText>
                    </h:panelGrid>
                    <BR/>
                    <h:panelGrid columns="2">
                        <h:outputLabel value="Nome Empresarial: " style="margin-left: 30px"/>
                        <h:inputText id="nomeEmpresarialFornecedor" value="#{PessoaBean.nome}" size="50" maxlength="150"
                                     style="margin-left: 8px;"
                                     required="true" requiredMessage="Campo Necessário: Nome Empresarial"
                                     onkeyup="UpperCase(this)" onkeydown="UpperCase(this)">
                            <rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip">
                                <span  style="white-space:nowrap">
                                    Ex: PONTOA CONSTRUTORA LTDA.
                                </span>
                            </rich:toolTip>
                        </h:inputText>
                    </h:panelGrid>
                    <h:panelGrid columns="2">
                        <h:outputLabel value="Nome Fantasia: " style="margin-left: 30px"/>
                        <h:inputText id="nomeFantasiaFornecedor" value="#{FornecedoresBean.nomeFantasia}" size="50" maxlength="150"
                                     style="margin-left: 32px;"
                                     required="true" requiredMessage="Campo Necessário: Nome Fantasia"
                                     onkeyup="UpperCase(this)" onkeydown="UpperCase(this)">
                            <rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip">
                                <span  style="white-space:nowrap">
                                    Ex: CONSTRUTORA PONTO A.
                                </span>
                            </rich:toolTip>
                        </h:inputText>

                    </h:panelGrid>
                    <h:panelGrid columns="4">
                        <h:outputLabel value="Insc. Estadual: " style="margin-left: 30px"/>
                        <h:inputText id="inscricaoEstadualFornecedor" value="#{FornecedoresBean.inscricaoEstadual}" size="12" maxlength="12"
                                     style="margin-left: 25px;"
                                     required="true" requiredMessage="Campo Necessário: Inscrição Estadual"
                                     onkeyup="MascaraInscricaoEstadual(this)" onkeydown="MascaraInscricaoEstadual(this)"/>
                        <h:outputLabel value="Insc. Municipal: " style="margin-left: 10px;"/>
                        <h:inputText id="inscricaoMunicipalFornecedor" value="#{FornecedoresBean.inscricaoMunicipal}" size="10" maxlength="10"
                                     required="true" requiredMessage="Campo Necessário: Inscrição Municipal"/>
                    </h:panelGrid>
                </h:panelGrid>

                <BR/>
                <rich:separator id="gridSeparatorContatos" height="3" width="711" style="margin-top: 20px; margin-bottom: 20px; margin-left: 35px;"/>
                <h:outputLabel id="outLabelContatos" value="Contatos"/>

                <BR/><BR/>

                <h:panelGrid id="gridTelefones">                   
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
