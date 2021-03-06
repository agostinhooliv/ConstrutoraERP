<%-- 
    Document   : cadastro
    Created on : 20/05/2011, 20:54:52
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
        <h:outputLabel value="Cadastro" styleClass="labelDefault" style="margin-left: 5px;"/>
        <a4j:form id="formCadastrosFuncionario">
            <a4j:include viewId="/paginas/funcionarios/templateFuncionarios.jsp"/>
            <a4j:include viewId="/paginas/funcionarios/mensagens.jsp"/>
            <a4j:commandButton value="    Cadastrar    " actionListener="#{FuncionariosBean.cadastrar}"
                               style="margin-left: 584px; margin-top: 20px;" styleClass="labelDefault"
                               onclick="Richfaces.showModalPanel('mensagens');"
                               oncomplete="Richfaces.hideModalPanel('mensagens'); Richfaces.showModalPanel('mensagens');"/>
        </a4j:form>
    </body>
</html>
