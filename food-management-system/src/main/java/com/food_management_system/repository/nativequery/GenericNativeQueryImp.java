package com.food_management_system.repository.nativequery;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class GenericNativeQueryImp implements GenericNativeQueries{

	
	 @PersistenceContext 
	 private EntityManager entityManager;
	 


	    /**
	     * Single query to check which field is invalid
	     */
	    public Object[] loginQuery(String username, String password, String hotelId) {

	        String sql =
	                "SELECT " +
	                        "CASE WHEN EXISTS (SELECT 1 FROM public.hotel_users WHERE username = :username) THEN true ELSE false END as username_valid, " +
	                        "CASE WHEN EXISTS (SELECT 1 FROM public.hotel_users WHERE username = :username AND password = :password) THEN true ELSE false END as password_valid, " +
	                        "CASE WHEN EXISTS (SELECT 1 FROM public.hotel_users WHERE username = :username AND hotel_id = :hotelId) THEN true ELSE false END as hotelId_valid, " +
	                        "CASE WHEN EXISTS (SELECT 1 FROM public.hotel_users WHERE username = :username AND password = :password AND hotel_id = :hotelId) THEN true ELSE false END as login_success";

	        Object[] result = (Object[]) entityManager
	                .createNativeQuery(sql)
	                .setParameter("username", username)
	                .setParameter("password", password)
	                .setParameter("hotelId", hotelId)
	                .getSingleResult();

	        return result;
	    }
	

}
