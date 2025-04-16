package com.ClubAccount_BE.core.response;

import static com.ClubAccount_BE.core.constant.CommonConstant.ONE_BASED_INDEX_PAGE;

import java.util.List;
import lombok.Builder;
import org.springframework.data.domain.Page;

@Builder
public record PagingResponse<T>(
        List<T> content,
        Long totalElements,
        int totalPages,
        int pageNumber,
        int pageSize,
        boolean hasNext,
        boolean hasPrevious,
        boolean isFirst,
        boolean isLast
) {

    public static <T> PagingResponse<T> of(Page<T> page) {
        return PagingResponse.<T>builder()
                .content(page.getContent())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .pageNumber(page.getNumber() + ONE_BASED_INDEX_PAGE)
                .pageSize(page.getSize())
                .hasNext(page.hasNext())
                .hasPrevious(page.hasPrevious())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}
