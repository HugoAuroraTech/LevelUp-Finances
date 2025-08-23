package br.com.levelupfinances.level_up_finances.domain.transaction.dto;

import java.math.BigDecimal;

public record TransactionSummaryDTO(
        BigDecimal totalIncome,
        BigDecimal totalExpense,
        BigDecimal balance
) {}
