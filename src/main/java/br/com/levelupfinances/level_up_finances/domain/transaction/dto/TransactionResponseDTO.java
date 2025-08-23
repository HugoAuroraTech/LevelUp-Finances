package br.com.levelupfinances.level_up_finances.domain.transaction.dto;

import br.com.levelupfinances.level_up_finances.domain.transaction.Category;
import br.com.levelupfinances.level_up_finances.domain.transaction.Transaction;
import br.com.levelupfinances.level_up_finances.domain.transaction.enums.TransactionType;

import java.math.BigDecimal;

public record TransactionResponseDTO(
        Long id,
        String title,
        String description,
        BigDecimal value,
        TransactionType transactionType,
        Long categoryId
) {
    public static TransactionResponseDTO fromEntity(Transaction transaction){
        return new TransactionResponseDTO(
                transaction.getId(),
                transaction.getTitle(),
                transaction.getDescription(),
                transaction.getValue(),
                transaction.getTransactionType(),
                transaction.getCategory().getId()
        );
    }
}
