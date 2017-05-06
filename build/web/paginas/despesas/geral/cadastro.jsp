<%-- 
    Document   : custosObras
    Created on : 23/05/2011, 17:59:59
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
        <a4j:form id="formCadastrosDespesas">
            <h:panelGrid id="gridDespesasGerais" columns="1">
                <h:panelGrid id="gridObra" columns="4">
                    <h:outputLabel value="Obra: "/>
                    <rich:comboBox id="obra" value="#{DespesasBean.obra}"
                                   enableManualInput="false" listWidth="200"
                                   style="margin-left: 36px;">
                        <f:selectItems value="#{DespesasBean.listaObras}"/>
                    </rich:comboBox>
                    <h:outputLabel value="Despesa:"/>
                    <rich:comboBox id="tipoDespesa" value="#{DespesasBean.tipoDespesa}" required="true"
                                   requiredMessage="Campo Necessário: Tipo Despesa"
                                   enableManualInput="false" listWidth="200"
                                   style="margin-left: 17px;">
                        <f:selectItems value="#{DespesasBean.listaDespesas}"/>
                    </rich:comboBox>
                </h:panelGrid>
                <h:panelGrid id="gridTipoDespesaValor" columns="4">
                    <h:outputLabel value="Valor:"/>
                    <h:inputText id="valorDespesa" value="#{DespesasBean.valorDespesa}" size="10" maxlength="14" 
                                 style="margin-left: 34px;"
                                 required="true" requiredMessage="Campo Necessário: Valor"/>
                    <h:outputLabel value="Observação:"
                                   style="margin-left: 116px;"/>
                    <h:inputTextarea value="#{DespesasBean.observacao}" cols="30" rows="2"/>
                </h:panelGrid>
                <a4j:commandButton value="   +   " style="margin-left: 700px;" actionListener="#{DespesasBean.adicionarDespesas}"
                                   reRender="gridDespesasGerais, painelSaida"/>
                <BR/><BR/>
                <a4j:outputPanel id="painelSaida" ajaxRendered="true">
                    <h:outputText value="#{DespesasBean.saida}"/>                    
                </a4j:outputPanel>
            </h:panelGrid>
        </a4j:form>
    </body>
</html>
