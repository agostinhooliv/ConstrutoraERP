<%-- 
    Document   : enderecos
    Created on : 13/05/2011, 14:12:40
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
        <script src="../../javascript/mascaras.js" type="text/javascript"></script>
    </head>
    <body>
        <h:panelGrid id="gridTipoEndereco_TipoLogradouro" columns="4" style="margin-left: 30px; margin-top: 30px;">
            <h:outputLabel value="Tipo Endereço:"/>
            <rich:comboBox id="tipoEndereco" value="#{EnderecosBean.tipoEndereco}" required="true"
                           requiredMessage="Campo Necessário: Tipo Endereço"
                           enableManualInput="false" width="100">
                <f:selectItems value="#{EnderecosBean.listaTipoEndereco}"/>
            </rich:comboBox>
            <h:outputLabel value="Tipo Logradouro: " style="margin-left: 20px;"/>
            <rich:comboBox id="tipoLogradouro" value="#{EnderecosBean.tipoLogradouro}" required="true" 
                           requiredMessage="Campo Necessário: Tipo Logradouro"
                           enableManualInput="true" width="60">
                <f:selectItems value="#{EnderecosBean.listaTipoLogradouro}"/>
            </rich:comboBox>
        </h:panelGrid>

        <h:panelGrid id="gridDadosEndereco" columns="1" style="margin-left: 30px; margin-top: 10px;">
            <h:panelGrid id="gridLogradouro" columns="2">
                <h:outputLabel value="Logradouro:"/>
                <h:inputText id="logradouro" value="#{EnderecosBean.logradouro}" size="61" maxlength="80" style="margin-left: 20px;"
                             onkeyup="UpperCase(this)" onkeydown="UpperCase(this)"/>
            </h:panelGrid>
            <h:panelGrid id="gridNumero_Complemento" columns="4">
                <h:outputLabel value="Numero:"/>
                <h:inputText id="numero" value="#{EnderecosBean.numero}" size="5" maxlength="6" style="margin-left: 50px;"
                             onkeyup="UpperCase(this)" onkeydown="UpperCase(this)"/>
                <h:outputLabel value="Complemento:" style="margin-left: 20px;"/>
                <h:inputText id="complemento" value="#{EnderecosBean.complemento}" size="30" maxlength="20"
                             onkeyup="UpperCase(this)" onkeydown="UpperCase(this)"/>
            </h:panelGrid>
            <h:panelGrid id="gridBairro_Cidade" columns="4">
                <h:outputLabel value="Bairro:"/>
                <h:inputText id="bairro" value="#{EnderecosBean.bairro}" size="20" maxlength="80" style="margin-left: 50px;"
                             onkeyup="UpperCase(this)" onkeydown="UpperCase(this)"/>
                <h:outputLabel value="Cidade:" style="margin-left: 20px;"/>
                <h:inputText id="cidade" value="#{EnderecosBean.cidade}" size="20" maxlength="20" style="margin-left: 10px;"
                             onkeyup="UpperCase(this)" onkeydown="UpperCase(this)"/>
            </h:panelGrid>
            <h:panelGrid id="gridEstado_CEP" columns="4">
                <h:panelGrid id="gridEstado" columns="2">
                    <h:outputLabel value="Estado:"/>
                    <rich:comboBox id="estados" value="#{EnderecosBean.estado}" enableManualInput="false" width="60">
                        <f:selectItems value="#{EnderecosBean.listaEstados}"/>
                    </rich:comboBox>
                </h:panelGrid>
                <h:panelGrid id="gridCEP" columns="2" style="margin-left: 10px;">
                    <h:outputLabel value="Cep:"/>
                    <h:inputText id="cep" value="#{EnderecosBean.cep}" size="10" maxlength="9" style="margin-left: 32px;"
                                 onkeyup="MascaraCEP(this)" onkeydown="MascaraCEP(this)"/>
                </h:panelGrid>
            </h:panelGrid>
        </h:panelGrid>
        <BR/>
    </body>
</html>
