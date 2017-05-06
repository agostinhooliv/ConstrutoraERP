/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.dao.financeiro;

import br.com.construtora.dao.HibernateUtil;
import br.com.construtora.model.financeiro.DespesasObra;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author agostinhooliv
 */
public class DespesasObraDAO {

    private Session session;

    public DespesasObraDAO() {
    }

    public void salvar(DespesasObra despesasObra) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(despesasObra);
        session.getTransaction().commit();
    }

    public void atualizar(DespesasObra despesasObra) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(despesasObra);
        session.getTransaction().commit();
    }

    public void apagar(DespesasObra despesasObra) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(despesasObra);
        session.getTransaction().commit();
    }

    public List<DespesasObra> findAll() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.getNamedQuery("DespesasObra.findAll");
        List<DespesasObra> resultado = q.list();
        session.getTransaction().commit();
        return resultado;
    }
}
