<%-- 
    Document   : cadastro
    Created on : 24/05/2011, 09:52:37
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
        <h:outputLabel value="Cadastro" styleClass="labelDefault" style="margin-left: 5px;"/>
        <a4j:form id="formCadastrosFornecedor">
            <a4j:include viewId="/paginas/fornecedores/templateFornecedores.jsp"/>
            <a4j:include viewId="/paginas/fornecedores/mensagens.jsp"/>
            <a4j:commandButton value="    Cadastrar    " actionListener="#{FornecedoresBean.cadastrar}"
                               style="margin-left: 584px; margin-top: 20px;" styleClass="labelDefault"
                               onclick="Richfaces.showModalPanel('mensagens');"
                               oncomplete="Richfaces.hideModalPanel('mensagens'); Richfaces.showModalPanel('mensagens');"/>
        </a4j:form>
    </body>
</html>
