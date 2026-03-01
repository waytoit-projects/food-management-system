package com.food_management_system.repository.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class GenericRepositoryImpl implements GenericRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <T> T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }
}
