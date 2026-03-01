package com.food_management_system.service;

import org.json.JSONObject;

public interface GenericService {
    <T> T save(T entity);
    
    JSONObject login(String json);
}
