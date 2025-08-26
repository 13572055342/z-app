package com.example.zapp.web;

import com.example.zapp.domain.FormTemplate;
import com.example.zapp.repository.FormTemplateRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/templates")
public class TemplateController {
	private final FormTemplateRepository repo;
	public TemplateController(FormTemplateRepository repo) { this.repo = repo; }

	@PostMapping
	public FormTemplate create(@RequestBody FormTemplate t) { return repo.save(t); }

	@GetMapping
	public List<FormTemplate> list() { return repo.findAll(); }

	@GetMapping("/{id}")
	public ResponseEntity<FormTemplate> get(@PathVariable Long id) {
		return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<FormTemplate> update(@PathVariable Long id, @RequestBody FormTemplate t) {
		return repo.findById(id).map(old -> {
			old.setName(t.getName());
			old.setDescription(t.getDescription());
			old.setSchemaJson(t.getSchemaJson());
			old.setIsPublished(t.getIsPublished());
			return ResponseEntity.ok(repo.save(old));
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (!repo.existsById(id)) return ResponseEntity.notFound().build();
		repo.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
