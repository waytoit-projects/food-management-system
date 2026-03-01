package com.food_management_system.repository.query;

public interface GenericRepository {
    <T> T save(T entity);
}
