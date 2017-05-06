<%-- 
    Document   : cadastro
    Created on : 12/05/2011, 16:11:41
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
        <h:outputLabel value="Cadastro" styleClass="labelDefault" style="margin-left: 5px;"/>
        <a4j:form id="formCadastrosCliente">
            <a4j:include viewId="/paginas/clientes/templateClientes.jsp"/>
            <a4j:include viewId="/paginas/clientes/mensagens.jsp"/>
            <a4j:commandButton value="    Cadastrar    " actionListener="#{ClientesBean.cadastrar}"
                               style="margin-left: 584px; margin-top: 20px;" styleClass="labelDefault"
                               onclick="Richfaces.showModalPanel('mensagens');"
                               oncomplete="Richfaces.hideModalPanel('mensagens'); Richfaces.showModalPanel('mensagens');"/>
        </a4j:form>
    </body>
</html>
