<%-- 
    Document   : cadastro
    Created on : 20/05/2011, 13:22:42
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
        <a4j:form id="formCadastrosObras">
            <a4j:include viewId="/paginas/obras/mensagens.jsp"/>
            <h:panelGrid styleClass="labelDefault">
                <h:panelGrid columns="2">
                    <h:outputLabel value="Nome: "/>
                    <h:inputText id="nomeObra" value="#{ObrasBean.nomeObra}" size="30" maxlength="60" style="margin-left: 5px;"
                                 required="true" requiredMessage="Campo Necessário: Nome"
                                 onkeyup="UpperCase(this)" onkeydown="UpperCase(this)"/>
                </h:panelGrid>
                <rich:separator id="gridSeparator" height="3" width="711" style="margin-top: 20px; margin-bottom: 20px; margin-left: 35px;"/>
                <a4j:include viewId="/paginas/enderecos.jsp"/>
                <a4j:commandButton value="    Cadastrar    " actionListener="#{ObrasBean.cadastrar}"
                                   style="margin-left: 584px; margin-top: 20px;" styleClass="labelDefault"
                                   onclick="Richfaces.showModalPanel('mensagens');"
                                   oncomplete="Richfaces.hideModalPanel('mensagens'); Richfaces.showModalPanel('mensagens');"/>
            </h:panelGrid>
        </a4j:form>
    </body>
</html>
