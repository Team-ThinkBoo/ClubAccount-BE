package com.ClubAccount_BE.receipt.adapter.out.persistence.entity;

import com.ClubAccount_BE.core.entity.TimeBaseEntity;
import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.receipt.domain.ReceiptItem;
import com.ClubAccount_BE.receipt.domain.type.ReceiptCategory;
import com.ClubAccount_BE.receipt.mapper.ReceiptItemMapper;
import com.ClubAccount_BE.user.adapter.out.persistence.entity.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Table(name = "receipt")
@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReceiptEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReceiptCategory category;

    @Column(nullable = false)
    private String businessName;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDate date;

    private String etc;

    private String receiptImageUrl;

    @Builder.Default
    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReceiptItemEntity> receiptItems = new ArrayList<>();

    public void updateReceipt(Receipt receipt) {
        this.category = receipt.getCategory();
        this.businessName = receipt.getBusinessName();
        this.amount = receipt.getAmount();
        this.date = receipt.getDate();
        this.etc = receipt.getEtc();
    }

    public void addReceiptItem(ReceiptItemEntity receiptItem) {
        this.receiptItems.add(receiptItem);
        if (receiptItem.getReceipt() != this) {
            receiptItem.addReceipt(this);
        }
    }

    public void replaceReceiptItem(List<ReceiptItem> receiptItems) {
        this.receiptItems.clear();
        receiptItems.stream()
                .map(ReceiptItemMapper::toEntity)
                .forEach(this::addReceiptItem);
    }
}
