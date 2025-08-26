package com.example.zapp.repository;

import com.example.zapp.domain.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QrCodeRepository extends JpaRepository<QrCode, Long> {
}
