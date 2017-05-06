<%-- 
    Document   : principal
    Created on : 24/05/2011, 09:50:44
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
        <link href="css/principal.css" rel="stylesheet" type="text/css" />
        <script src="javascript/mascaras.js" type="text/javascript"></script>
    </head>
    <body>
        <rich:panel id="painelPrincipalFornecedor" bodyClass="rich-laguna-panel-no-header" style="width: 800px;">
            <BR/>
            <a4j:form id="a4jFormFornecedor">

                <a4j:commandLink value="1" actionListener="#{FornecedoresBean.acoesPagina}" reRender="painelPrincipalFornecedor"
                                 style="margin-left: 8px;">
                    <f:attribute name="acao" value="cadastro"/>
                </a4j:commandLink>
                <a4j:commandLink value="2" actionListener="#{FornecedoresBean.acoesPagina}" reRender="painelPrincipalFornecedor"
                                 style="margin-left: 10px;">
                    <f:attribute name="acao" value="alteracao"/>
                </a4j:commandLink>
                <a4j:commandLink value="3" actionListener="#{FornecedoresBean.acoesPagina}" reRender="painelPrincipalFornecedor"
                                 style="margin-left: 10px;">
                    <f:attribute name="acao" value="remocao"/>
                </a4j:commandLink>
                <a4j:commandLink value="4" actionListener="#{FornecedoresBean.acoesPagina}" reRender="painelPrincipalFornecedor"
                                 style="margin-left: 10px;">
                    <f:attribute name="acao" value="lista"/>
                </a4j:commandLink>
            </a4j:form>


            <h:outputLabel value="#{MenuBean.currentTitulo}" style="margin-left: 350px;" styleClass="labelTitulo"/>

            <rich:separator height="5" style="margin-top: 20px;"/>

            <BR/><BR/>
            <a4j:outputPanel ajaxRendered="true">
                <a4j:include id="painelCadastroFornecedor" rendered="#{FornecedoresBean.verCadastroFornecedor}" viewId="/paginas/fornecedores/cadastro.jsp"/>
                <a4j:include id="painelAlteracaoFornecedor" rendered="#{FornecedoresBean.verAlteracaoFornecedor}" viewId="/paginas/fornecedores/alteracao.jsp"/>
                <a4j:include id="painelRemocaoFornecedor" rendered="#{FornecedoresBean.verRemocaoFornecedor}" viewId="/paginas/fornecedores/remocao.jsp"/>
                <a4j:include id="painelListaFornecedor" rendered="#{FornecedoresBean.verListaFornecedor}" viewId="/paginas/fornecedores/lista.jsp"/>
            </a4j:outputPanel>
        </rich:panel>
    </body>
</html>
