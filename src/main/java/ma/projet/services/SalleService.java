package ma.projet.services;


import ma.projet.dao.IDao;
import ma.projet.entities.Salle;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import ma.projet.util.HibernateUtil;


public class SalleService implements IDao<Salle> {

    @Override
    public boolean create(Salle o) {
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
          //  session.close();
        }
        return false;
    }

    @Override
    public boolean delete(Salle o) {
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
    public boolean update(Salle o) {
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
    public Salle findById(int id) {
        Session session = null;
        Salle e  = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            e = (Salle) session.get(Salle.class, id);
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
    public List<Salle> findAll() {
        Session session = null;
        List<Salle>  salles = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            salles = session.createQuery("from Salle").list();
            session.getTransaction().commit();
            return salles;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }finally{
            session.close();
        }
        return salles;
    }

}
