package com.ClubAccount_BE.receipt.adapter.out.persistence.repository;

import com.ClubAccount_BE.receipt.adapter.out.persistence.entity.ReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<ReceiptEntity, Long> {

}
