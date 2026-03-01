package com.food_management_system.serviceimpl;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.food_management_system.repository.nativequery.GenericNativeQueries;
import com.food_management_system.repository.query.GenericRepository;
import com.food_management_system.service.GenericService;

@Service
public class GenericServiceImpl implements GenericService {

    @Autowired
    private GenericRepository genericRepository;
    
    @Autowired
    private GenericNativeQueries genericNativeQueries;

    @Transactional
    @Override
    public <T> T save(T entity) {
        return genericRepository.save(entity);
    }
    
    @Override
    public JSONObject login(String json) {

        JSONObject request = new JSONObject(json);
        String username = request.getString("username");
        String password = request.getString("password");
        String hotelId = request.getString("hotelId");

        Object[] result = genericNativeQueries.loginQuery(username, password, hotelId);

        // Cast to Boolean
        boolean usernameValid = (Boolean) result[0];
        boolean passwordValid = (Boolean) result[1];
        boolean hotelIdValid  = (Boolean) result[2];
        boolean loginSuccess  = (Boolean) result[3];

        JSONObject response = new JSONObject();

        if (loginSuccess) {
            response.put("success", true);
            response.put("message", "Login Successful");
        } else {
            response.put("success", false);
            response.put("message", "Invalid Credentials");

            JSONObject invalidFields = new JSONObject();
            if (!usernameValid) invalidFields.put("username", "Username not found");
            else {
                if (!passwordValid) invalidFields.put("password", "Incorrect password");
                if (!hotelIdValid) invalidFields.put("hotelId", "Invalid hotelId");
            }

            response.put("invalidFields", invalidFields);
        }

        return response;
    }
}
