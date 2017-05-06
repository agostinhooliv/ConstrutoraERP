package br.com.construtora.bean;

import br.com.construtora.dao.UsuarioDAO;
import br.com.construtora.model.Usuario;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author agostinho
 */
public class LoginBean {

    private String login;
    private String senha;
    private String perfil;
    private FacesContext fc;
    private final UsuarioDAO usuarioDAO;
    private Boolean logado;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        usuarioDAO = new UsuarioDAO();
    }

    public String efetuarLogin() {

        // Call Web Service Operation
        setFc(FacesContext.getCurrentInstance());

        Usuario usuario = usuarioDAO.findByLogin(login);
                
        if (usuario != null &&
                usuario.getDeSenh().equals(senha)) {
            
            setPerfil(usuario.getPerfilidPerfil().getDePerf());
            HttpSession session = (HttpSession) getFc().getExternalContext().getSession(true);
            session.setAttribute("usuario", getLogin());
            session.setAttribute("perfil", getPerfil());
            setLogado(Boolean.TRUE);
            //Encerra a sessão se a mesma estiver inativa por 5 min
            //session.setMaxInactiveInterval(300);
            return "logado";
        }

        setLogado(Boolean.FALSE);
        return null;
    }

    public String efetuarLogoff() {

        setFc(FacesContext.getCurrentInstance());
        HttpSession session = (HttpSession) getFc().getExternalContext().getSession(true);

        if (session != null) {
            session.invalidate();
            return "logoff";
        }

        return null;
    }

    public FacesContext getFc() {
        return fc;
    }

    public void setFc(FacesContext fc) {
        this.fc = fc;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Boolean getLogado() {
        return logado;
    }

    public void setLogado(Boolean logado) {
        this.logado = logado;
    }
}
