package com.food_management_system.controller;

import java.util.Map;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import jakarta.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import jakarta.servlet.http.Cookie;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.food_management_system.security.JwtUtil;
import com.food_management_system.service.GenericService;

@Controller
public class LoginController {

	@Autowired
	private GenericService genericService;

	@Autowired
    private JwtUtil jwtUtil;
	
//	@PostMapping("/login")
//	public @ResponseBody String login(@RequestBody String json){
//
//	    return genericService.login(json).toString();
//	}
	
	 @PostMapping("/login")
	    public @ResponseBody String login(@RequestBody String json,
	                                      HttpServletResponse response) {

	        // 1️ Call your existing service
	        JSONObject serviceResponse = genericService.login(json);

	        boolean success = serviceResponse.getBoolean("success");

	        if (success) {

	            // 2️ Extract username again from request JSON
	            JSONObject request = new JSONObject(json);
	            String username = request.getString("username");

	            // 3️ Generate JWT
	            String token = jwtUtil.generateToken(username);

	            // 4️ Create HttpOnly Cookie
	            Cookie cookie = new Cookie("jwt", token);
	            cookie.setHttpOnly(true);
	            cookie.setSecure(false); // TRUE in production (HTTPS)
	            cookie.setPath("/");
	            cookie.setMaxAge(60 * 60 * 24); // 1 day

	            response.addCookie(cookie);
	        }

	        // 5️ Return your existing JSON response
	        return serviceResponse.toString();
	    }
}
