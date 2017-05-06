/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.util;

import javax.faces.event.ActionEvent;

/**
 *
 * @author agostinho
 */
public class FacesUtil {

    public static String getActionAttribute(ActionEvent event, String name) {
        return (String) event.getComponent().getAttributes().get(name);
    }
}
