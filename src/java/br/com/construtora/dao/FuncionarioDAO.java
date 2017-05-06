/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.construtora.dao;

import br.com.construtora.exceptions.CpfCadastradoException;
import br.com.construtora.model.Funcionario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author agostinho
 */
public class FuncionarioDAO {

    private Session session;

    public FuncionarioDAO() {
    }

    public void salvar(Funcionario funcionario) throws CpfCadastradoException {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(funcionario);
        session.getTransaction().commit();
    }

    public void atualizar(Funcionario funcionario) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(funcionario);
        session.getTransaction().commit();
    }

    public void apagar(Funcionario funcionario) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(funcionario);
        session.getTransaction().commit();
    }

    public List<Funcionario> findAll() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.getNamedQuery("Funcionario.findAll");
        List<Funcionario> resultado = q.list();
        session.getTransaction().commit();
        return resultado;
    }

    public Funcionario findByCPF(String cpf) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.getNamedQuery("Funcionario.findByCpf");
        q.setString("cpf", cpf);
        Funcionario funcionario = (Funcionario) q.uniqueResult();
        session.getTransaction().commit();
        return funcionario;
    }
}
