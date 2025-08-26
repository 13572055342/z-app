package com.example.zapp.web;

import com.example.zapp.domain.FormSubmission;
import com.example.zapp.domain.FormTemplate;
import com.example.zapp.repository.FormSubmissionRepository;
import com.example.zapp.repository.FormTemplateRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {
	private final FormSubmissionRepository repo;
	private final FormTemplateRepository templateRepo;
	public SubmissionController(FormSubmissionRepository repo, FormTemplateRepository templateRepo) {
		this.repo = repo; this.templateRepo = templateRepo;
	}

	@PostMapping
	public ResponseEntity<FormSubmission> create(@RequestBody FormSubmission s) {
		FormTemplate t = templateRepo.findById(s.getTemplate().getId()).orElse(null);
		if (t == null || !Boolean.TRUE.equals(t.getIsPublished())) return ResponseEntity.badRequest().build();
		s.setTemplate(t);
		return ResponseEntity.ok(repo.save(s));
	}

	@GetMapping
	public List<FormSubmission> list(@RequestParam(value = "template_id", required = false) Long templateId) {
		return repo.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<FormSubmission> get(@PathVariable Long id) {
		return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
}
