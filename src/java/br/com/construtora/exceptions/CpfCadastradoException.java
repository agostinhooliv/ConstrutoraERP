/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.exceptions;

/**
 *
 * @author agostinhooliv
 */
public class CpfCadastradoException extends Exception {
    
    String msg = "CPF JÁ CADASTRADO!";

    /**
     * Creates a new instance of <code>CpfCadastradoException</code> without
     * detail message.
     */
    public CpfCadastradoException() {
    }

    /**
     * Constructs an instance of <code>CpfCadastradoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CpfCadastradoException(String msg) {
        super(msg);
    }
    
    @Override
    public String getMessage(){
        return msg;
    }
}
