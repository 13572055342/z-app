package com.example.zapp.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {
	@GetMapping("/health")
	public Map<String, String> health() {
		Map<String, String> m = new HashMap<>();
		m.put("status", "ok");
		return m;
	}
}
