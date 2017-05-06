/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.dao;

import br.com.construtora.model.Endereco;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author agostinho
 */
public class EnderecoDAO {

    private Session session;

    public EnderecoDAO() {
    }

    public void salvar(Endereco endereco) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(endereco);
        session.getTransaction().commit();
    }

    public void atualizar(Endereco endereco) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(endereco);
        session.getTransaction().commit();
    }

    public void apagar(Endereco endereco) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(endereco);
        session.getTransaction().commit();
    }

    public List<Endereco> findAll() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.getNamedQuery("Endereco.findAll");
        List<Endereco> resultado = q.list();
        session.getTransaction().commit();
        return resultado;
    }
}
