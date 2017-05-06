/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.dao;

import br.com.construtora.model.Cliente;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author agostinho
 */
public class ClienteDAO {

    private Session session;

    public ClienteDAO() {
    }

    public void salvar(Cliente cliente) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(cliente);
        session.getTransaction().commit();
    }

    public void atualizar(Cliente cliente) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(cliente);
        session.getTransaction().commit();
    }

    public void apagar(Cliente cliente) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(cliente);
        session.getTransaction().commit();
    }

    public List<Cliente> findAll() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.getNamedQuery("Cliente.findAll");
        List<Cliente> resultado = q.list();
        session.getTransaction().commit();
        return resultado;
    }

    public Cliente findByCPF(String cpf) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.getNamedQuery("Cliente.findByCpf");
        q.setString("cpf", cpf);
        Cliente cliente = (Cliente) q.uniqueResult();
        session.getTransaction().commit();
        return cliente;
    }
}
