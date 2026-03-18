package com.food_management_system.serviceimpl;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.food_management_system.repository.nativequery.GenericNativeQueries;
import com.food_management_system.repository.query.GenericRepository;
import com.food_management_system.service.GenericService;
import com.food_management_system.entity.MenuItem;
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
    
    @Transactional
    @Override
    public JSONObject saveFoodItem(JSONObject request) {
    	
    	  JSONObject response = new JSONObject();

          try {

        	  MenuItem item = new MenuItem();

              item.setItemCode(request.optString("itemCode"));
              item.setItemName(request.optString("itemName"));
              item.setMainCategory(request.optString("mainCategory"));
              item.setSubCategory(request.optString("subCategory"));
              item.setBrandName(request.optString("brandName"));
              item.setItemType(request.optString("itemType"));
              item.setIsVeg(request.optBoolean("isVeg"));
              item.setSize(request.optString("size"));
              item.setUnit(request.optString("unit"));
              item.setCostPrice(request.optDouble("costPrice"));
              item.setSellingPrice(request.optDouble("sellingPrice"));
              item.setGstPercentage(request.optDouble("gstPercentage"));
              item.setGstAmount(request.optDouble("gstAmount"));
              item.setHsnCode(request.optString("hsnCode"));
              item.setStockQuantity(request.optInt("stockQuantity"));
              item.setMinStockAlert(request.optInt("minStockAlert"));
              item.setPreparationTime(request.optInt("preparationTime"));
              item.setSpicyLevel(request.optString("spicyLevel"));
              item.setDescription(request.optString("description"));
              item.setImageUrl(request.optString("imageUrl"));
              item.setIsAvailable(request.optBoolean("isAvailable"));
              item.setIsActive(request.optBoolean("isActive"));

              genericRepository.save(item);

              response.put("success", true);
              response.put("message", "Food item saved successfully");

          } catch (Exception e) {

              response.put("success", false);
              response.put("message", e.getMessage());
              e.printStackTrace();
          }

          return response;
    	
    }
}
