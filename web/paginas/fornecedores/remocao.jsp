<%-- 
    Document   : remocao
    Created on : 24/05/2011, 09:52:57
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
        <a4j:form id="a4jFormRemocaoFornecedores">
            <a4j:include viewId="/paginas/fornecedores/mensagens.jsp"/>
            <h:outputLabel value="Remoção" styleClass="labelDefault" style="margin-left: 5px;"/>
            <BR/><BR/>
            <h:panelGrid columns="3" style="margin-left: 300px;"  styleClass="labelDefault">
                <h:outputLabel value="CPF: " style="margin-left: 30px"/>
                <rich:comboBox id="cpfFornecedoresRemocao" value="#{FornecedoresBean.cnpj}" defaultLabel="Escolha o cnpj de um fornecedor..." width="172">
                    <f:selectItems value="#{FornecedoresBean.listaCnpjFornecedores}"/>
                </rich:comboBox>
                <a4j:commandButton id="botaoPesquisar" actionListener="#{FornecedoresBean.pesquisarFornecedor}" value="Pesquisar..."
                                   style="margin-left: 10px;" styleClass="labelDefault"
                                   onclick="Richfaces.showModalPanel('mensagens');Richfaces.hideModalPanel('mensagens');Richfaces.showModalPanel('mensagens');"
                                   data="#{FornecedoresBean.existeFornecedor}"
                                   oncomplete="if(data == true){Richfaces.hideModalPanel('mensagens');}
                                   if(data == false){Richfaces.hideModalPanel('mensagens'); Richfaces.showModalPanel('mensagens');}"/>
            </h:panelGrid>
        </a4j:form>

        <a4j:form id="hFormRemocaoFornecedor">
            <rich:modalPanel  tridentIVEngineSelectBehavior="hide" id="confirmacao" height="80"  width="240"  zindex="1000"
                              resizeable="false" moveable="false" top="50" autosized="true">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confimação"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:outputText value="Tem certeza que deseja remover o fornecedor?" style="margin-top:50px;" escape="false"/>
                <h:messages id="confirmacaoAlert" errorStyle="color: #E65D32;" infoStyle="color: #0051E5" warnStyle="color: #FAAC17"/>
                <BR/><BR/><BR/><BR/><BR/><BR/>
                <a4j:commandButton value="  Sim  " actionListener="#{FornecedoresBean.remover}" style="margin-left: 55px;"
                                   onclick="Richfaces.showModalPanel('confirmacao');"
                                   oncomplete="Richfaces.hideModalPanel('confirmacao'); Richfaces.showModalPanel('mensagens');"/>
                <a4j:commandButton value="  Não  "  actionListener="#{FornecedoresBean.fechar}" style="margin-left: 10px;"
                                   onclick="Richfaces.hideModalPanel('confirmacao');"/>
            </rich:modalPanel>

            <a4j:include viewId="/paginas/fornecedores/templateFornecedores.jsp" style="margin-top:500px;"/>
        </a4j:form>

        <a4j:commandButton id="botaoRemover" value="    Remover    " disabled="#{FornecedoresBean.habilitarBotaoRemover}"
                           style="margin-left: 606px; margin-top: 20px;" styleClass="labelDefault"
                           onclick="Richfaces.showModalPanel('confirmacao');"/>
    </body>
</html>
