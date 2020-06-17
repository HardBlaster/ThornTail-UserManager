package hu.innovitech.database.repository.impl;

import hu.innovitech.database.repository.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public abstract class JpaDao<E, K> implements DAO<E, K> {

    protected Class<E> entityClass;

    @PersistenceContext(unitName = "persistence")
    protected EntityManager entityManager;

    public JpaDao(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Transactional
    public void persist(E entity) {
        entityManager.persist(entity);
    }

    @Transactional
    public void update(E entity) {
        entityManager.merge(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    @Transactional
    public void remove(E entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    public E findById(K id) {
        return entityManager.find(entityClass, id);
    }

    public List<E> findAll() {
        return entityManager.createQuery("FROM " + entityClass.getSimpleName(), entityClass).getResultList();
    }
}
