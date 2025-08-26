package com.example.zapp.web;

import com.example.zapp.domain.FormTemplate;
import com.example.zapp.domain.QrCode;
import com.example.zapp.repository.FormTemplateRepository;
import com.example.zapp.repository.QrCodeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.List;

@RestController
@RequestMapping("/api/qrcodes")
public class QrCodeController {
	private final QrCodeRepository repo;
	private final FormTemplateRepository templateRepo;
	private final SecureRandom random = new SecureRandom();
	public QrCodeController(QrCodeRepository repo, FormTemplateRepository templateRepo) {
		this.repo = repo; this.templateRepo = templateRepo;
	}

	@PostMapping
	public ResponseEntity<QrCode> create(@RequestBody QrCode q) {
		FormTemplate t = templateRepo.findById(q.getTemplate().getId()).orElse(null);
		if (t == null) return ResponseEntity.badRequest().build();
		if (q.getCode() == null || q.getCode().isEmpty()) {
			q.setCode(generate(10));
		}
		q.setTemplate(t);
		return ResponseEntity.ok(repo.save(q));
	}

	@GetMapping
	public List<QrCode> list() { return repo.findAll(); }

	@PatchMapping("/{id}")
	public ResponseEntity<QrCode> toggle(@PathVariable Long id, @RequestBody QrCode body) {
		return repo.findById(id).map(q -> {
			if (body.getIsActive() != null) q.setIsActive(body.getIsActive());
			return ResponseEntity.ok(repo.save(q));
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (!repo.existsById(id)) return ResponseEntity.notFound().build();
		repo.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	private String generate(int len) {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) sb.append(chars.charAt(random.nextInt(chars.length())));
		return sb.toString();
	}
}
