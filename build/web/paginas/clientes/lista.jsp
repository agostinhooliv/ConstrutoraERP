<%-- 
    Document   : lista
    Created on : 12/05/2011, 16:40:25
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
        <h:outputLabel value="Lista" styleClass="labelDefault" style="margin-left: 5px;"/>
        <a4j:form id="a4jFormListaClientes">
            <rich:dataTable id="listaClientes" value="#{ClientesBean.listaClientes}" var="clientes"
                            style="margin-left: 10%;">
                <f:facet name="header">
                    <rich:columnGroup>
                        <rich:column>
                            <h:outputText value="Tipo"/>
                        </rich:column>
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
                            <h:outputText value="Fone Residencial"/>
                        </rich:column>
                        <rich:column>
                            <h:outputText value="Fone Celular"/>
                        </rich:column>
                    </rich:columnGroup>
                </f:facet>

                <rich:columnGroup>
                    <rich:column style="text-align: center;">
                        <h:outputText value="#{clientes.idTpPess}"/>
                    </rich:column>
                    <rich:column>
                        <h:outputText value="#{clientes.nmPess}"/>
                    </rich:column>
                    <rich:column>
                        <h:outputText value="#{clientes.cpf}"/>                            
                    </rich:column>
                    <rich:column>
                        <h:outputText value="#{clientes.enderecoIdEndereco.sgTpLogr}. #{clientes.enderecoIdEndereco.nmLogr} - #{clientes.enderecoIdEndereco.nrEnde}"/>
                    </rich:column>
                    <rich:column>
                        <h:outputText value="(#{clientes.dddFone1}) #{clientes.fone1}"/>
                    </rich:column>
                    <rich:column>
                        <h:outputText value="(#{clientes.dddFone2}) #{clientes.fone2}"/>
                    </rich:column>
                </rich:columnGroup>

                <f:facet name="footer">
                    <rich:datascroller></rich:datascroller>
                </f:facet>
            </rich:dataTable>
        </a4j:form>
    </body>
</html>
