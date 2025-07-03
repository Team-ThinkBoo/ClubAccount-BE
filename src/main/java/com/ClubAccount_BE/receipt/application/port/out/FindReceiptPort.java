package com.ClubAccount_BE.receipt.application.port.out;

import com.ClubAccount_BE.receipt.domain.CategoryExpenseResult;
import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.receipt.domain.MonthlyExpenseResult;
import com.ClubAccount_BE.user.domain.User;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindReceiptPort {

    Page<Receipt> getReceiptList(
            User user,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    );

    List<Receipt> getAllReceipts(User user);

    Receipt getReceipt(User user, Long receiptId);

    List<MonthlyExpenseResult> getReceiptExpenseByMonth(User user, int year);

    List<CategoryExpenseResult> getReceiptExpenseByCategory(User user);
}
