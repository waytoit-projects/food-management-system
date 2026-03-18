package com.food_management_system.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.food_management_system.security.JwtUtil;
import com.food_management_system.service.GenericService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FoodItemController {

    @Autowired
    private GenericService genericService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/food-item")
    public @ResponseBody String saveFoodItem(@RequestBody String json,
                                             HttpServletRequest request) {

        JSONObject response = new JSONObject();

        try {

            String token = null;

            // 1️ Read JWT from HttpOnly cookie
            Cookie[] cookies = request.getCookies();

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("jwt".equals(cookie.getName())) {
                        token = cookie.getValue();
                    }
                }
            }

            // 2️ Validate token
            if (token == null || !jwtUtil.validateToken(token)) {

                response.put("success", false);
                response.put("message", "Unauthorized access");

                return response.toString();
            }

            // 3️ Call service if token valid
            JSONObject requestJson = new JSONObject(json);

            JSONObject serviceResponse = genericService.saveFoodItem(requestJson);

            return serviceResponse.toString();

        } catch (Exception e) {

            response.put("success", false);
            response.put("message", e.getMessage());

            return response.toString();
        }
    }
}