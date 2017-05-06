<%-- 
    Document   : mensagens
    Created on : 18/05/2011, 16:41:58
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
        <rich:modalPanel  tridentIVEngineSelectBehavior="hide" id="mensagens" height="80"  width="240"  zindex="1000"
                          resizeable="false" moveable="false" top="50" autosized="true">
            <f:facet name="header">
                <h:panelGroup>
                    <h:outputText value="Processando requisição"></h:outputText>
                </h:panelGroup>
            </f:facet>          
            <h:outputText value="#{MensagensBean.mensagem}" style="margin-top:50px;" escape="false"/>
            <h:messages id="mensagensAlert" errorStyle="color: #E65D32;" infoStyle="color: #0051E5" warnStyle="color: #FAAC17"/>
            <BR/><BR/>
            <h:commandLink value="  Fechar  " style="margin-left: 90px;" onclick="Richfaces.hideModalPanel('mensagens');" actionListener="#{ClientesBean.fechar}"/>

        </rich:modalPanel>
    </body>
</html>
