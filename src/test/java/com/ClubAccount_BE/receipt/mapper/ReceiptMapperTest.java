package com.ClubAccount_BE.receipt.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.ClubAccount_BE.factory.receipt.ReceiptTestFactory;
import com.ClubAccount_BE.receipt.adapter.out.persistence.entity.ReceiptEntity;
import com.ClubAccount_BE.receipt.domain.Receipt;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReceiptMapperTest {

    private final ReceiptMapper receiptMapper = new ReceiptMapper();

    @Test
    @DisplayName("영수증 매퍼 클래스 테스트")
    void mapToJpaEntity() {
        // given
        Receipt receipt = ReceiptTestFactory.createReceipt();

        // when
        ReceiptEntity entity = receiptMapper.mapToJpaEntity(receipt);

        // then
        assertThat(entity.getId()).isEqualTo(receipt.getId());
        assertThat(entity.getUser()).isEqualTo(receipt.getUser());
        assertThat(entity.getCategory()).isEqualTo(receipt.getCategory().getDisplayName());
        assertThat(entity.getBusinessName()).isEqualTo(receipt.getBusinessName());
        assertThat(entity.getAmount()).isEqualTo(receipt.getAmount());
        assertThat(entity.getDate()).isEqualTo(receipt.getDate());
        assertThat(entity.getEtc()).isEqualTo(receipt.getEtc());
        assertThat(entity.getReceiptImageUrl()).isEqualTo(receipt.getReceiptImageUrl());
    }
}