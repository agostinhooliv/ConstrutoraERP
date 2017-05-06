<%-- 
    Document   : menu
    Created on : 12/05/2011, 14:42:29
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
        <title>menu</title>        
    </head>
    <body>
        <rich:toolBar height="30px;" width="1024px;">
            <rich:dropDownMenu>
                <f:facet name="label">
                    <h:panelGroup>
                        <h:outputText value="Cadastros"/>
                    </h:panelGroup>
                </f:facet>              
                <rich:menuItem submitMode="ajax" value="Cliente" actionListener="#{MenuBean.mudaConteudoPrincipal}">
                    <f:attribute name="pagina" value="paginas/clientes/principal"/>
                    <f:attribute name="titulo" value="Clientes"/>
                </rich:menuItem>
                <rich:menuItem submitMode="ajax" value="Funcionário" actionListener="#{MenuBean.mudaConteudoPrincipal}">
                    <f:attribute name="pagina" value="paginas/funcionarios/principal"/>
                    <f:attribute name="titulo" value="Funcionários"/>
                </rich:menuItem>               
                <rich:menuItem submitMode="ajax" value="Fornecedor" actionListener="#{MenuBean.mudaConteudoPrincipal}">
                    <f:attribute name="pagina" value="paginas/fornecedores/principal"/>
                    <f:attribute name="titulo" value="Fornecedores"/>
                </rich:menuItem>
                <rich:menuItem submitMode="ajax" value="Obra" actionListener="#{MenuBean.mudaConteudoPrincipal}">
                    <f:attribute name="pagina" value="paginas/obras/principal"/>
                    <f:attribute name="titulo" value="Obras"/>
                </rich:menuItem>
            </rich:dropDownMenu>

            <rich:dropDownMenu rendered="false">
                <f:facet name="label">
                    <h:panelGroup>
                        <h:outputText value="Documentos"/>
                    </h:panelGroup>
                </f:facet>
                <rich:menuGroup value="Relatórios...">
                </rich:menuGroup>     
                <rich:menuSeparator id="separatorDocumentos"/>
                <rich:menuItem submitMode="ajax" value="Doc. Cadastrais" actionListener="#{MenuBean.mudaConteudoPrincipal}">
                    <f:attribute name="pagina" value="paginas/"/>
                    <f:attribute name="titulo" value="Documentos"/>
                </rich:menuItem>
            </rich:dropDownMenu>

            <rich:dropDownMenu>
                <f:facet name="label">
                    <h:panelGroup>
                        <h:outputText value="Financeiro"/>
                    </h:panelGroup>
                </f:facet>                              
                <rich:menuGroup value="Despesas...">
                    <rich:menuGroup value="Material Obra...">
                        <rich:menuItem submitMode="ajax" value="Cadastrar" actionListener="#{MenuBean.mudaConteudoPrincipal}">
                            <f:attribute name="pagina" value="paginas/obras/custosObras/principal"/>
                            <f:attribute name="titulo" value="Obras - Custos"/>
                        </rich:menuItem>
                        <rich:menuItem submitMode="ajax" value="Importar notas" actionListener="#{MenuBean.mudaConteudoPrincipal}">
                            <f:attribute name="pagina" value="paginas/obras/custosObras/principal"/>
                            <f:attribute name="titulo" value="Obras - Custos"/>
                        </rich:menuItem>
                    </rich:menuGroup>
                    <rich:menuGroup value="Pessoal...">
                        <rich:menuItem submitMode="ajax" value="Salários" actionListener="#{MenuBean.mudaConteudoPrincipal}">
                            <f:attribute name="pagina" value="paginas/pessoal/"/>
                            <f:attribute name="titulo" value="Pessoal - Salários"/>
                        </rich:menuItem>
                    </rich:menuGroup>
                    <rich:menuGroup value="Bens..." rendered="false">
                        <rich:menuGroup value="Ferramentas...">
                            <rich:menuItem submitMode="ajax" value="Cadastrar" actionListener="#{MenuBean.mudaConteudoPrincipal}">
                                <f:attribute name="pagina" value="paginas/bens/"/>
                                <f:attribute name="titulo" value="Bens - Ferramentas"/>
                            </rich:menuItem>
                        </rich:menuGroup>
                    </rich:menuGroup>
                    <rich:menuGroup value="Gerais...">
                        <rich:menuItem submitMode="ajax" value="Cadastrar" actionListener="#{MenuBean.mudaConteudoPrincipal}">
                            <f:attribute name="pagina" value="paginas/despesas/geral/principal"/>
                            <f:attribute name="titulo" value="Despesas Gerais"/>
                        </rich:menuItem>
                    </rich:menuGroup>
                </rich:menuGroup>              
            </rich:dropDownMenu>
        </rich:toolBar>
    </body>
</html>
