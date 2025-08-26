package com.example.zapp.repository;

import com.example.zapp.domain.FormSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormSubmissionRepository extends JpaRepository<FormSubmission, Long> {
}
