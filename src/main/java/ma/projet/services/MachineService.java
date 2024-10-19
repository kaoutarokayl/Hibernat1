package ma.projet.services;


import ma.projet.dao.IDao;
import ma.projet.entities.Machine;
import ma.projet.entities.Salle;

import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import ma.projet.util.HibernateUtil;
import org.hibernate.Transaction;

public class MachineService implements IDao<Machine> {

    @Override
    public boolean create(Machine o) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }finally{
            session.close();
        }
        return false;
    }

    @Override
    public boolean delete(Machine o) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(o);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }finally{
            session.close();
        }
        return false;
    }

    @Override
    public boolean update(Machine o) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(o);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }finally{
            session.close();
        }
        return false;
    }

    @Override
    public Machine findById(int id) {
        Session session = null;
        Machine e  = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            e = (Machine) session.get(Machine.class, id);
            session.getTransaction().commit();
            return e;
        } catch (HibernateException ex) {
            session.getTransaction().rollback();
        }finally{
            session.close();
        }
        return e;
    }

    @Override
    public List<Machine> findAll() {
        Session session = null;
        List<Machine>  machines = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            machines = session.createQuery("from Machine").list();
            session.getTransaction().commit();
            return machines;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }finally{
            session.close();
        }
        return machines;
    }

    public List<Machine> findBetweenDate(Date d1, Date d2) {
        Session session = null;
        Transaction tx = null;
        List<Machine> machines = null;

        try {
            // Récupération d'une session Hibernate
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            // Exécution de la requête nommée avec les paramètres de date
            machines = session.getNamedQuery("findBetweenDate")
                    .setParameter("d1", d1)
                    .setParameter("d2", d2)
                    .list();

            tx.commit();
        } catch (HibernateException e) {
            // Gestion des erreurs: rollback de la transaction et affichage du stacktrace
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            // Fermeture de la session
            if (session != null) {
                session.close();
            }
        }

        return machines;
    }


}