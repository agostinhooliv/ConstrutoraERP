/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.dao.financeiro;

import br.com.construtora.dao.HibernateUtil;
import br.com.construtora.model.financeiro.DespesasGeral;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author agostinhooliv
 */
public class DespesasGeralDAO {
    
     private Session session;

    public DespesasGeralDAO() {
    }

    public void salvar(DespesasGeral despesasGeral) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(despesasGeral);
        session.getTransaction().commit();
    }

    public void atualizar(DespesasGeral despesasGeral) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(despesasGeral);
        session.getTransaction().commit();
    }

    public void apagar(DespesasGeral despesasGeral) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(despesasGeral);
        session.getTransaction().commit();
    }

    public List<DespesasGeral> findAll() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.getNamedQuery("DespesasGeral.findAll");
        List<DespesasGeral> resultado = q.list();
        session.getTransaction().commit();
        return resultado;
    }

    public DespesasGeral findByTipoDespesas(String tipoDespesa) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.getNamedQuery("DespesasGeral.findByTpDesp");
        q.setParameter("tpDesp", tipoDespesa);
        DespesasGeral despesasGeral = (DespesasGeral) q.uniqueResult();
        session.getTransaction().commit();
        return despesasGeral;
    }
}
