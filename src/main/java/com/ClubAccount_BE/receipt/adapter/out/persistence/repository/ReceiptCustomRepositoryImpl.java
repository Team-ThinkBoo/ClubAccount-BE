package com.ClubAccount_BE.receipt.adapter.out.persistence.repository;

import com.ClubAccount_BE.receipt.adapter.out.persistence.entity.QReceiptEntity;
import com.ClubAccount_BE.receipt.adapter.out.persistence.entity.ReceiptEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@RequiredArgsConstructor
public class ReceiptCustomRepositoryImpl implements ReceiptCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ReceiptEntity> findAllByDate(
            Long userId,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    ) {
        QReceiptEntity receipt = QReceiptEntity.receiptEntity;

        BooleanBuilder where = new BooleanBuilder();
        where.and(receipt.user.id.eq(userId));

        if (startDate != null && endDate != null) {
            where.and(receipt.date.between(startDate, endDate));
        }

        List<ReceiptEntity> content = queryFactory
                .selectFrom(receipt)
                .where(where)
                .orderBy(receipt.date.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory
                .select(receipt.count())
                .from(receipt)
                .where(where);

        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }
}
