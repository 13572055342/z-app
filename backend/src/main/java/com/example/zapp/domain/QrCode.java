package com.example.zapp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "qr_codes")
@Getter
@Setter
public class QrCode {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "template_id")
	private FormTemplate template;

	@Column(length = 64, unique = true)
	private String code;

	@Column(nullable = false)
	private Boolean isActive = true;

	@Column(nullable = false)
	private Instant createdAt = Instant.now();
}
