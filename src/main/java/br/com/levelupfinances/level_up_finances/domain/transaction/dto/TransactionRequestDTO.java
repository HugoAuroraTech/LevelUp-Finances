package br.com.levelupfinances.level_up_finances.domain.transaction.dto;

import br.com.levelupfinances.level_up_finances.domain.transaction.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record TransactionRequestDTO(
        @NotBlank(message = "O titulo da transação é obrigatório")
        @Size(max = 100, message = "O título deve ter no máximo 100 caracteres")
        String title,
        String description,
        @NotNull
        BigDecimal value,
        @NotNull
        TransactionType transactionType,
        @NotNull
        Long userId,
        @NotNull
        Long categoryId
) {
}
