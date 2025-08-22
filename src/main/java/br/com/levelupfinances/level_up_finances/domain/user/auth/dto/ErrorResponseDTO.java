package br.com.levelupfinances.level_up_finances.domain.user.auth.dto;

public record ErrorResponseDTO(
        String code,
        String message
) {}