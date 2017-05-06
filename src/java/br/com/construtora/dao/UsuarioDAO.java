/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.dao;

import br.com.construtora.model.Usuario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author agostinhooliv
 */
public class UsuarioDAO {
    private Session session;

    public UsuarioDAO() {
    }

    public void salvar(Usuario usuario) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(usuario);
        session.getTransaction().commit();
    }

    public void atualizar(Usuario usuario) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(usuario);
        session.getTransaction().commit();
    }

    public void apagar(Usuario usuario) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(usuario);
        session.getTransaction().commit();
    }

    public List<Usuario> findAll() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.getNamedQuery("Usuario.findAll");
        List<Usuario> resultado = q.list();
        session.getTransaction().commit();
        return resultado;
    }

    public Usuario findByLogin(String login) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.getNamedQuery("Usuario.findByDeLogi");
        q.setString("deLogi", login);
        Usuario usuario = (Usuario) q.uniqueResult();
        session.getTransaction().commit();
        return usuario;
    }

}
