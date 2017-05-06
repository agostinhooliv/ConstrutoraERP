<%-- 
    Document   : login
    Created on : 12/05/2011, 12:36:00
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
        <title> .:: Construtora .a ::. Login</title>
        <link href="css/principal.css" rel="stylesheet" type="text/css" />        
    </head>
    <f:view>
        <body class="principal">
            
            <rich:modalPanel tridentIVEngineSelectBehavior="hide" id="loginInvalido" height="80"  width="240"  zindex="1000"
                              resizeable="false" moveable="false" top="50" autosized="true">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Login Incorreto!"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:outputText value="Login ou Senha Inválida!" style="margin-top:50px; margin-left: 35px;" escape="false"/>
                <br/><br/><br/>
                <a4j:commandButton value="  Fechar  " style="margin-left: 40%; margin-right: 40%;"
                                   onclick="Richfaces.hideModalPanel('loginInvalido');"/>

            </rich:modalPanel>
                
            <h:form id="form1">
                <rich:panel id="painelPrincipal" header="Dados de Login" styleClass="painelLogin">
                    <h:panelGrid styleClass="labelDefault" style="margin-left: 20px; margin-top: 10px;">
                        <h:panelGrid id="painelLogin" columns="2" style="margin-left: 20px;">
                            <h:outputLabel value="Login: "/>
                            <h:inputText value="#{LoginBean.login}"/>
                        </h:panelGrid>
                        <h:panelGrid id="painelSenha" columns="2" style="margin-left: 20px;">
                            <h:outputLabel value="Senha: "/>
                            <h:inputSecret value="#{LoginBean.senha}"/>
                        </h:panelGrid>
                        <a4j:commandButton id="botaoEntrar" value="    Entrar    " 
                                         styleClass="labelDefault" style="margin-left: 40%; margin-right: 40%; margin-top: 15px;"
                                         data="#{LoginBean.logado}"
                                         action="#{LoginBean.efetuarLogin}"
                                         oncomplete="Richfaces.showModalPanel('loginInvalido');">                            
                        </a4j:commandButton>
                    </h:panelGrid>
                </rich:panel>
            </h:form>
        </body>
    </f:view>
</html>
