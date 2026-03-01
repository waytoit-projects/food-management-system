package com.food_management_system.controller;

import java.util.Map;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.food_management_system.service.GenericService;

@Controller
public class LoginController {

	@Autowired
	private GenericService genericService;

	@Autowired
	private EntityManager entityManager;

	@GetMapping("/login")
	public String loginPage() {
		return "login"; // login.jsp
	}

	@PostMapping("/generic/save")
	@ResponseBody
	public Object save(@RequestBody Map<String, Object> payload) {
		// 1. Mandatory Validation: Every value must not be empty/null to allow insert
		for (Map.Entry<String, Object> entry : payload.entrySet()) {
			if (entry.getValue() == null || entry.getValue().toString().trim().isEmpty()) {
				return Map.of("status", "error", "message", entry.getKey() + " is mandatory to allow insert the data.");
			}
		}

		try {
			Class<?> targetEntityClass = null;

			// 2. Dynamically resolve Entity via Metamodel attributes comparison
			for (EntityType<?> entityType : entityManager.getMetamodel().getEntities()) {
				boolean isMatch = true;
				boolean hasAtLeastOneKey = false;

				for (String key : payload.keySet()) {
					try {
						entityType.getAttribute(key);
						hasAtLeastOneKey = true;
					} catch (IllegalArgumentException e) {
						isMatch = false; // The payload key is not an attribute in this Entity
						break;
					}
				}

				if (isMatch && hasAtLeastOneKey) {
					// 3. Ensure all mandatory entity fields are present (besides auto-generated
					// IDs)
					for (var attribute : entityType.getAttributes()) {
						String attrName = attribute.getName();
						if (!attrName.equals("id") && !payload.containsKey(attrName)) {
							// Return exact missing key response
							return Map.of("status", "error", "message",
									attrName + " is mandatory to allow insert the data.");
						}
					}

					targetEntityClass = entityType.getJavaType();
					break; // Entity perfectly matched completely
				}
			}

			if (targetEntityClass == null) {
				return Map.of("status", "error", "message",
						"JSON payload does not match any database Entity structure.");
			}

			// 4. Convert and Persist dynamically
			ObjectMapper mapper = new ObjectMapper();
			Object entityToSave = mapper.convertValue(payload, targetEntityClass);

			Object savedResponse = genericService.save(entityToSave);

			return Map.of("status", "success", "message", "Data saved successfully", "data", savedResponse);

		} catch (Exception e) {
			return Map.of("status", "error", "message", "Error executing generic save: " + e.getMessage());
		}
	}
}
