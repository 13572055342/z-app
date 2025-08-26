package com.example.zapp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "form_templates")
@Getter
@Setter
public class FormTemplate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 255)
	private String name;

	@Lob
	private String description;

	@Lob
	@Column(name = "schema_json", nullable = false)
	private String schemaJson;

	@Column(nullable = false)
	private Boolean isPublished = false;

	@Column(nullable = false)
	private Instant createdAt = Instant.now();

	@Column(nullable = false)
	private Instant updatedAt = Instant.now();

	@PreUpdate
	public void onUpdate() { this.updatedAt = Instant.now(); }
}
