<%-- 
    Document   : lista
    Created on : 20/05/2011, 20:54:17
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
        <h:outputLabel value="Lista" styleClass="labelDefault" style="margin-left: 5px;"/>
        <a4j:form id="a4jFormListaFuncionarios">
            <rich:dataTable id="listaClientes" value="#{FuncionariosBean.listaFuncionario}" var="funcionarios"
                            style="margin-left: 10%;">
                <f:facet name="header">
                    <rich:columnGroup>                      
                        <rich:column>
                            <h:outputText value="Nome"/>
                        </rich:column>
                        <rich:column>
                            <h:outputText value="CPF"/>
                        </rich:column>
                        <rich:column>
                            <h:outputText value="Endereço"/>
                        </rich:column>
                        <rich:column>
                            <h:outputText value="Carteira Trabalho"/>
                        </rich:column>
                        <rich:column>
                            <h:outputText value="Pis"/>
                        </rich:column>
                        <rich:column>
                            <h:outputText value="Fone Residencial"/>
                        </rich:column>
                        <rich:column>
                            <h:outputText value="Setor/Cargo"/>
                        </rich:column>
                        <rich:column>
                            <h:outputText value="Status"/>
                        </rich:column>
                    </rich:columnGroup>
                </f:facet>

                <rich:columnGroup>
                    <rich:column>
                        <h:outputText value="#{funcionarios.nmPess}"/>
                    </rich:column>
                    <rich:column>
                        <h:outputText value="#{funcionarios.cpf}"/>
                    </rich:column>
                    <rich:column>
                        <h:outputText value="#{funcionarios.enderecoIdEndereco.sgTpLogr}. #{funcionarios.enderecoIdEndereco.nmLogr} - #{funcionarios.enderecoIdEndereco.nrEnde}"/>
                    </rich:column>
                    <rich:column>
                        <h:outputText value="#{funcionarios.nrCartTrab}"/>
                    </rich:column>
                    <rich:column>
                        <h:outputText value="#{funcionarios.nrPis}"/>
                    </rich:column>
                    <rich:column>
                        <h:outputText value="(#{funcionarios.dddFone1}) #{funcionarios.fone1}"/>
                    </rich:column>                  
                    <rich:column>
                        <h:outputText value="#{funcionarios.deSetor}/#{funcionarios.deCargo}"/>
                    </rich:column>
                     <rich:column>
                        <h:outputText value="#{funcionarios.sgStat}"/>
                    </rich:column>
                </rich:columnGroup>

                <f:facet name="footer">
                    <rich:datascroller></rich:datascroller>
                </f:facet>
            </rich:dataTable>
        </a4j:form>
    </body>
</html>
