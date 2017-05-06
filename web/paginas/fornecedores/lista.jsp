<%-- 
    Document   : lista
    Created on : 24/05/2011, 09:53:04
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
        <a4j:form id="a4jFormListaFornecedores">
            <rich:dataTable id="listaFornecedores" value="#{FornecedoresBean.listaFornecedores}" var="fornecedores"
                            style="margin-left: 10%;">
                <f:facet name="header">
                    <rich:columnGroup>
                        <rich:column>
                            <h:outputText value="Nome Fantasia"/>
                        </rich:column>
                        <rich:column>
                            <h:outputText value="CNPJ"/>
                        </rich:column>
                        <rich:column>
                            <h:outputText value="Insc. Estadual"/>
                        </rich:column>
                        <rich:column>
                            <h:outputText value="Insc. Municipal"/>
                        </rich:column>
                        <rich:column>
                            <h:outputText value="Fone Comercial"/>
                        </rich:column>
                        <rich:column>
                            <h:outputText value="Endereço"/>
                        </rich:column>                        
                    </rich:columnGroup>
                </f:facet>

                <rich:columnGroup>
                    <rich:column>
                        <h:outputText value="#{fornecedores.nmFant}"/>
                    </rich:column>
                    <rich:column>
                        <h:outputText value="#{fornecedores.cnpj}"/>
                    </rich:column>
                    <rich:column>
                        <h:outputText value="#{fornecedores.inscEsta}"/>
                    </rich:column>
                    <rich:column>
                        <h:outputText value="#{fornecedores.inscMuni}"/>
                    </rich:column>
                    <rich:column>
                        <h:outputText value="#{fornecedores.enderecoIdEndereco.sgTpLogr}. #{fornecedores.enderecoIdEndereco.nmLogr} - #{fornecedores.enderecoIdEndereco.nrEnde}"/>
                    </rich:column>                    
                    <rich:column>
                        <h:outputText value="(#{fornecedores.dddFone2}) #{fornecedores.fone2}"/>
                    </rich:column>                   
                </rich:columnGroup>

                <f:facet name="footer">
                    <rich:datascroller></rich:datascroller>
                </f:facet>
            </rich:dataTable>
        </a4j:form>
    </body>
</html>
