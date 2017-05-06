/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.bean;

import br.com.construtora.util.FacesUtil;
import javax.faces.event.ActionEvent;

/**
 *
 * @author agostinho
 */
public class MenuBean {

    private String currentLink = "branco";
    private String currentTitulo = "";

    /** Creates a new instance of MenuBean */
    public MenuBean() {
    }

    public void mudaConteudoPrincipal(ActionEvent ev) {

        String link = (String) FacesUtil.getActionAttribute(ev, "pagina");
        String titulo = (String) FacesUtil.getActionAttribute(ev, "titulo");

        setCurrentLink(link);
        setCurrentTitulo(titulo.toUpperCase());
    }

    public String getCurrentLink() {
        return currentLink;
    }

    public void setCurrentLink(String currentLink) {
        this.currentLink = currentLink;
    }

    public String getCurrentTitulo() {
        return currentTitulo;
    }

    public void setCurrentTitulo(String currentTitulo) {
        this.currentTitulo = currentTitulo;
    }
}
