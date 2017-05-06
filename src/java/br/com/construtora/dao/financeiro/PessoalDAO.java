/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.dao.financeiro;

import br.com.construtora.dao.HibernateUtil;
import br.com.construtora.dao.*;
import br.com.construtora.model.Funcionario;
import br.com.construtora.model.financeiro.Pessoal;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author agostinho
 */
public class PessoalDAO {

    private Session session;

    public PessoalDAO() {
    }

    public void salvar(Pessoal pessoal) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(pessoal);
        session.getTransaction().commit();
    }

    public void atualizar(Pessoal pessoal) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(pessoal);
        session.getTransaction().commit();
    }

    public void apagar(Pessoal pessoal) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(pessoal);
        session.getTransaction().commit();
    }

    public List<Pessoal> findAll() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.getNamedQuery("Pessoal.findAll");
        List<Pessoal> resultado = q.list();
        session.getTransaction().commit();
        return resultado;
    }

    public Pessoal findByTpMate(String tpMate) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.getNamedQuery("Pessoal.findByTpMate");
        q.setString("tpMate", tpMate);
        Pessoal pessoal = (Pessoal) q.uniqueResult();
        session.getTransaction().commit();
        return pessoal;
    }

    public Pessoal findByFuncDtPaga(Funcionario funcionario, Date dtDesp) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.getNamedQuery("Pessoal.findByFuncDtPaga");
        q.setParameter("funcionario", funcionario);
        q.setDate("dtDesp", dtDesp);
        Pessoal pessoal = (Pessoal) q.uniqueResult();
        session.getTransaction().commit();
        return pessoal;
    }
}
