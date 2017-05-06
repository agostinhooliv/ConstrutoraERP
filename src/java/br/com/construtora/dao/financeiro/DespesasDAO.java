/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.dao.financeiro;

import br.com.construtora.dao.HibernateUtil;
import br.com.construtora.dao.*;
import br.com.construtora.model.financeiro.Despesas;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author agostinho
 */
public class DespesasDAO {

    private Session session;

    public DespesasDAO() {
    }

    public void salvar(Despesas despesas) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(despesas);
        session.getTransaction().commit();
    }

    public void atualizar(Despesas despesas) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(despesas);
        session.getTransaction().commit();
    }

    public void apagar(Despesas despesas) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(despesas);
        session.getTransaction().commit();
    }

    public List<Despesas> findAll() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.getNamedQuery("Despesas.findAll");
        List<Despesas> resultado = q.list();
        session.getTransaction().commit();
        return resultado;
    }

    public Despesas findByDtDesp(Date dtDesp) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.getNamedQuery("Despesas.findByDtDesp");
        q.setDate("dtDesp", dtDesp);
        Despesas despesas = (Despesas) q.uniqueResult();
        session.getTransaction().commit();
        return despesas;
    }
}
