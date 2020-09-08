/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unal.etl.sakila;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author edwar
 */
public class DbManager {

    static DbManager db;
    private EntityManager em;

    private DbManager() {

    }

    public static DbManager getInstance() {
        if (db == null) {
            db = new DbManager();
        }
        return db;
    }

    private EntityManager getEntityManager() {
        if (em == null || !em.isOpen()) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("cco.edu.unal.etl.sakila_etl-sakila_jar_1.0PU");
            em = emf.createEntityManager();
        }
        return em;
    }

    public <T> T findById(Object id, Class<T> classT) {
        T obj = null;
        getEntityManager().getTransaction().begin();
        try {
            obj = em.find(classT, id);
        } catch (Exception e) {
            e.printStackTrace();
            getEntityManager().getTransaction().rollback();
        } finally {
            getEntityManager().close();
        }
        return (T) obj;
    }

    public List listFromHql(String hql) {
        List l = new ArrayList();

        getEntityManager().getTransaction().begin();
        try {
            l = getEntityManager().createQuery(hql).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            getEntityManager().getTransaction().rollback();
        } finally {
            getEntityManager().close();
        }

        return l;
    }

    public List listFromSql(String sql) {
        List l = new ArrayList();

        getEntityManager().getTransaction().begin();
        try {
            l = getEntityManager().createNativeQuery(sql).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            getEntityManager().getTransaction().rollback();
        } finally {
            getEntityManager().close();
        }

        return l;
    }

    public void save(Object object) {
        getEntityManager().getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            getEntityManager().getTransaction().rollback();
        } finally {
            getEntityManager().close();
        }
    }

    public void update(String sql) {
        getEntityManager().getTransaction().begin();
        try {
            em.createNativeQuery(sql).executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            getEntityManager().getTransaction().rollback();
        } finally {
            getEntityManager().close();
        }
    }

}
