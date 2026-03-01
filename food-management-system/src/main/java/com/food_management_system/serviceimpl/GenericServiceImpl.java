package com.food_management_system.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.food_management_system.repository.crud.GenericRepository;
import com.food_management_system.service.GenericService;

@Service
public class GenericServiceImpl implements GenericService {

    @Autowired
    private GenericRepository genericRepository;

    @Transactional
    @Override
    public <T> T save(T entity) {
        return genericRepository.save(entity);
    }
}
