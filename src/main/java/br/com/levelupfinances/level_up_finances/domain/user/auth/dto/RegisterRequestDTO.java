package br.com.levelupfinances.level_up_finances.domain.user.auth.dto;

import br.com.levelupfinances.level_up_finances.domain.user.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequestDTO(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password,
        @NotNull
        UserRole role
) {
}
