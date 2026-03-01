package com.food_management_system.repository.crud;

public interface GenericRepository {
    <T> T save(T entity);
}
