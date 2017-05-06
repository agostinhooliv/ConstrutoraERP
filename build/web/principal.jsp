<%-- 
    Document   : principal
    Created on : 12/05/2011, 13:16:34
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
        <title>.:: Construtora .a ::. Principal</title>
        <link href="css/principal.css" rel="stylesheet" type="text/css" />        
    </head>
    <body>
        <f:view>            
            <h:panelGrid id="painelPrincipal" columnClasses="gridContent" style="margin-left: 90px;">
                <h:form id="hForm">
                    <h:panelGrid id="painelLogoff" columns="2" columnClasses="gridContent" styleClass="topo">
                        <h:panelGrid columns="2">
                            <img src="imagens/logo.png" style="margin-top: 20px; margin-left: 10px;" width="50" height="47"
                                 alt="Logo .a" />
                        </h:panelGrid>
                        <rich:panel styleClass="labelDefault" id="login" style="width: 400px; margin-left: 390px;">
                            <f:facet name="header">
                                <h:panelGroup>
                                    <h:outputText value="Dados de Login "/>
                                </h:panelGroup>
                            </f:facet>
                            <h:outputText value="Login: " styleClass="labelDefault" /> <h:outputText value="#{LoginBean.login.toUpperCase()}"/>
                            <h:outputText value="Perfil: " styleClass="labelDefault" style="margin-left: 20px;"/> <h:outputText value="#{LoginBean.perfil}"/>
                            <h:commandButton id="Sair" value="  Sair  " action="#{LoginBean.efetuarLogoff}" style="margin-left: 15px;" styleClass="labelDefault"/>
                        </rich:panel>
                    </h:panelGrid>

                    <a4j:include viewId="menu.jsp"/>
                </h:form>

                <h:panelGrid style="margin-left: 10%; margin-top: 10px;">
                    <a4j:outputPanel ajaxRendered="true">
                        <a4j:include viewId="#{MenuBean.currentLink}.jsp"/>                        
                    </a4j:outputPanel>
                </h:panelGrid>

                <h:panelGrid columns="1" columnClasses="gridContent" styleClass="bottom" style="margin-top: 10px;">
                    <h:outputText value="Versão:  00000" style="margin-left: 885px;"/>
                </h:panelGrid>
            </h:panelGrid>

        </f:view>
    </body>
</html>
