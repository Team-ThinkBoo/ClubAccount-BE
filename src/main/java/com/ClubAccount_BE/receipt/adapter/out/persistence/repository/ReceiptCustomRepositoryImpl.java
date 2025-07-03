package com.ClubAccount_BE.receipt.adapter.out.persistence.repository;

import static com.ClubAccount_BE.receipt.adapter.out.persistence.entity.QReceiptEntity.receiptEntity;

import com.ClubAccount_BE.receipt.adapter.out.persistence.entity.ReceiptEntity;
import com.ClubAccount_BE.receipt.domain.CategoryExpenseResult;
import com.ClubAccount_BE.receipt.domain.MonthlyExpenseResult;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
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
    public Page<ReceiptEntity> findByDate(
            Long userId,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    ) {

        BooleanBuilder where = new BooleanBuilder();
        where.and(receiptEntity.user.id.eq(userId));

        if (startDate != null && endDate != null) {
            where.and(receiptEntity.date.between(startDate, endDate));
        }

        List<ReceiptEntity> content = queryFactory
                .selectFrom(receiptEntity)
                .where(where)
                .orderBy(receiptEntity.date.desc(), receiptEntity.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory
                .select(receiptEntity.count())
                .from(receiptEntity)
                .where(where);

        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }

    @Override
    public List<MonthlyExpenseResult> calculateExpensesByMonth(Long userId, int year) {
        return queryFactory
                .select(Projections.constructor(
                        MonthlyExpenseResult.class,
                        Expressions.constant(year),
                        receiptEntity.date.month(),
                        receiptEntity.amount.sum()
                ))
                .from(receiptEntity)
                .where(
                        receiptEntity.user.id.eq(userId),
                        receiptEntity.date.year().eq(year)
                )
                .groupBy(receiptEntity.date.month())
                .fetch();
    }

    @Override
    public List<CategoryExpenseResult> calculateExpensesByCategory(Long userId) {
        return queryFactory
                .select(Projections.constructor(CategoryExpenseResult.class,
                        receiptEntity.category,
                        receiptEntity.amount.sum()
                ))
                .from(receiptEntity)
                .where(receiptEntity.user.id.eq(userId))
                .groupBy(receiptEntity.category)
                .fetch();
    }
}
