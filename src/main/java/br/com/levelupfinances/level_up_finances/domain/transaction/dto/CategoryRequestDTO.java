package br.com.levelupfinances.level_up_finances.domain.transaction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CategoryRequestDTO(
        @NotBlank(message = "O título da categoria é obrigatório")
        @Size(max = 100, message = "O título deve ter no máximo 100 caracteres")
        String title,
        @NotBlank(message = "A cor da categoria é obrigatória")
        @Pattern(regexp = "^#[0-9A-Fa-f]{6}$", message = "A cor deve estar no formato hexadecimal válido (ex: #FF5733)")
        String color
) {
}
