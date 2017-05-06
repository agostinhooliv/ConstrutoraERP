<%-- 
    Document   : alteracao
    Created on : 20/05/2011, 20:53:57
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
        <a4j:form id="a4jFormPesquisaAlteracaoFuncionarios">
            <a4j:include viewId="/paginas/funcionarios/mensagens.jsp"/>
            <h:outputLabel value="Alteração" styleClass="labelDefault" style="margin-left: 5px;"/>
            <BR/><BR/>
            <h:panelGrid columns="3" style="margin-left: 300px;" styleClass="labelDefault">
                <h:outputLabel value="CPF: " style="margin-left: 30px"/>
                <rich:comboBox id="cpfFuncionariosAlteracao" value="#{FuncionariosBean.cpf}" defaultLabel="Escolha o cpf de um funcionário..." width="172">
                    <f:selectItems value="#{FuncionariosBean.listaCpfFuncionario}"/>
                </rich:comboBox>
                <a4j:commandButton id="botaoPesquisar" value="Pesquisar..." actionListener="#{FuncionariosBean.pesquisarFuncionarios}"
                                   style="margin-left: 10px;" styleClass="labelDefault"
                                   data="#{FuncionariosBean.existeFuncionario}"
                                   onclick="Richfaces.showModalPanel('mensagens');"
                                   oncomplete="if(data == false){Richfaces.hideModalPanel('mensagens'); Richfaces.showModalPanel('mensagens');};
                                   if(data == true){Richfaces.hideModalPanel('mensagens');}"/>

            </h:panelGrid>
        </a4j:form>

        <a4j:form id="a4jFormAlteracaoCliente">
            <rich:modalPanel  tridentIVEngineSelectBehavior="hide" id="confirmacao" height="80"  width="240"  zindex="1000"
                              resizeable="false" moveable="false" top="50" autosized="true">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confimação"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:outputText value="Tem certeza que deseja alterar os dados do funcionário?" style="margin-top:50px;" escape="false"/>
                <h:messages id="confirmacaoAlert" errorStyle="color: #E65D32;" infoStyle="color: #0051E5" warnStyle="color: #FAAC17"/>
                <BR/><BR/><BR/><BR/><BR/><BR/>
                <a4j:commandButton value="  Sim  " actionListener="#{FuncionariosBean.alterar}" style="margin-left: 55px;"
                                   onclick="Richfaces.showModalPanel('confirmacao');"
                                   oncomplete="Richfaces.hideModalPanel('confirmacao'); Richfaces.showModalPanel('mensagens');"/>
                <a4j:commandButton value="  Não  "  actionListener="#{FuncionariosBean.fechar}" style="margin-left: 10px;"
                                   onclick="Richfaces.hideModalPanel('confirmacao');"/>

            </rich:modalPanel>

            <a4j:include viewId="/paginas/funcionarios/templateFuncionarios.jsp" style="margin-top:500px;"/>
        </a4j:form>

        <a4j:commandButton id="botaoAtualizar" value="    Alterar    " disabled="#{FuncionariosBean.habilitarBotaoAtualizar}"
                           style="margin-left: 606px; margin-top: 20px;" styleClass="labelDefault"
                           onclick="Richfaces.showModalPanel('confirmacao');"/>
    </body>
</html>
