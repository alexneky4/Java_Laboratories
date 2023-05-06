package uaic.info.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import uaic.info.model.AbstractEntity;
import uaic.info.persistance.PersistenceManager;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractRepository <T extends AbstractEntity,ID extends Serializable> {

    protected EntityManager em;
    private Class<T> entityClass;

    public AbstractRepository(Class<T> entityClass)
    {
        this.em = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        this.entityClass = entityClass;
    }

    public void create(T entity)
    {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(entity);
        et.commit();
    }

    public T findById(ID id)
    {
        return em.find(entityClass,id);
    }

    public List<T> findByName(String name)
    {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.where(cb.like(root.get("name"), "%" + name + "%"));
        TypedQuery<T> query = em.createQuery(cq);
        return query.getResultList();
    }

    public List<T> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root);
        TypedQuery<T> query = em.createQuery(cq);
        return query.getResultList();
    }

    public void deleteAll() {
        EntityTransaction et = em.getTransaction();
        et.begin();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<T> cd = cb.createCriteriaDelete(entityClass);
        cd.from(entityClass);
        Query query = em.createQuery(cd);
        query.executeUpdate();
        et.commit();
    }

    public void deleteByName(String name) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<T> cd = cb.createCriteriaDelete(entityClass);
        Root<T> root = cd.from(entityClass);
        cd.where(cb.equal(root.get("name"), name));
        Query query = em.createQuery(cd);
        query.executeUpdate();
        et.commit();
    }

    public void closeManager()
    {
        em.close();
    }


}
