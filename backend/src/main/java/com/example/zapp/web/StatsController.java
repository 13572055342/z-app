package com.example.zapp.web;

import com.example.zapp.repository.FormSubmissionRepository;
import com.example.zapp.repository.FormTemplateRepository;
import com.example.zapp.repository.QrCodeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
public class StatsController {
	private final FormTemplateRepository templates;
	private final FormSubmissionRepository submissions;
	private final QrCodeRepository qrcodes;
	public StatsController(FormTemplateRepository templates, FormSubmissionRepository submissions, QrCodeRepository qrcodes) {
		this.templates = templates; this.submissions = submissions; this.qrcodes = qrcodes;
	}
	@GetMapping("/overview")
	public Map<String, Long> overview() {
		Map<String, Long> m = new HashMap<>();
		m.put("templates", templates.count());
		m.put("submissions", submissions.count());
		m.put("qrcodes", qrcodes.count());
		return m;
	}
}
