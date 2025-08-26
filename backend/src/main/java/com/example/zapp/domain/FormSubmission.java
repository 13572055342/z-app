package com.example.zapp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "form_submissions")
@Getter
@Setter
public class FormSubmission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "template_id")
	private FormTemplate template;

	@Column(length = 128)
	private String submitterOpenid;

	@Lob
	@Column(nullable = false)
	private String dataJson;

	@Column(nullable = false)
	private Instant createdAt = Instant.now();
}
