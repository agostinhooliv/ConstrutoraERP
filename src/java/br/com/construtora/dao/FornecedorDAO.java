/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.dao;

import br.com.construtora.model.Fornecedor;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author agostinho
 */
public class FornecedorDAO {

    private Session session;

    public FornecedorDAO() {
    }

    public void salvar(Fornecedor fornecedor) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(fornecedor);
        session.getTransaction().commit();
    }

    public void atualizar(Fornecedor fornecedor) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(fornecedor);
        session.getTransaction().commit();
    }

    public void apagar(Fornecedor fornecedor) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(fornecedor);
        session.getTransaction().commit();
    }

    public List<Fornecedor> findAll() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.getNamedQuery("Fornecedor.findAll");
        List<Fornecedor> resultado = q.list();
        session.getTransaction().commit();
        return resultado;
    }

    public Fornecedor findByCNPJ(String cnpj) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.getNamedQuery("Fornecedor.findByCnpj");
        q.setString("cnpj", cnpj);
        Fornecedor fornecedor = (Fornecedor) q.uniqueResult();
        session.getTransaction().commit();
        return fornecedor;
    }
}
