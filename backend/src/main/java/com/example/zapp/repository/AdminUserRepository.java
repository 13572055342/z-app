package com.example.zapp.repository;

import com.example.zapp.domain.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {
}
