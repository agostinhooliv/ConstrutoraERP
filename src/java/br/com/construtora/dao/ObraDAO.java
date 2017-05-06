/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.dao;

import br.com.construtora.model.Obra;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author agostinho
 */
public class ObraDAO {

    private Session session;

    public ObraDAO() {
    }

    public void salvar(Obra obra) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(obra);
        session.getTransaction().commit();
    }

    public void atualizar(Obra obra) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(obra);
        session.getTransaction().commit();
    }

    public void apagar(Obra obra) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(obra);
        session.getTransaction().commit();
    }

    public List<Obra> findAll() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.getNamedQuery("Obra.findAll");
        List<Obra> resultado = q.list();
        session.getTransaction().commit();
        return resultado;
    }

    public Obra findByDeObra(String deObra) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.getNamedQuery("Obra.findByDeObra");
        q.setString("deObra", deObra);
        Obra obra = (Obra) q.uniqueResult();
        session.getTransaction().commit();
        return obra;
    }
}
