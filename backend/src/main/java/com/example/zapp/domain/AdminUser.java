package com.example.zapp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "admin_users")
@Getter
@Setter
public class AdminUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 64, unique = true)
	private String username;

	@Column(length = 255)
	private String passwordHash;

	@Column(nullable = false)
	private Boolean isActive = true;

	@Column(nullable = false)
	private Instant createdAt = Instant.now();
}
