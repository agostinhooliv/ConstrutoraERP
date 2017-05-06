package br.com.construtora.bean;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author agostinho
 */
public class MensagensBean {

    private String mensagem = "Processando requisição, por favor aguarde...";

    /** Creates a new instance of MensagensBean */
    public MensagensBean() {
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
